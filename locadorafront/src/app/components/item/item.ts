import { Component, signal } from '@angular/core';
import { ItemInterface, ItemService } from '../../services/item-service';
import { TituloInterface, TituloService } from '../../services/titulo-service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-item',
  imports: [FormsModule, CommonModule],
  templateUrl: './item.html',
  styleUrl: './item.css',
})
export class Item {
  itens = signal<ItemInterface[]>([]);
  titulos = signal<TituloInterface[]>([]);

  mostrarFormulario = signal(false);
  mensagemErro = signal('');

  tiposItem: string[] = ['DVD', 'Blu-ray', 'VHS', 'Fita Cassete'];

  itemAtual: ItemInterface = {
    id: 0,
    numSerie: 0,
    dtAquisicao: '',
    tipoItem: '',
    titulo: {} as TituloInterface,
  };

  tituloIdSelecionado: number | null = null;

  constructor(
    private itemService: ItemService,
    private tituloService: TituloService
  ) {}

  ngOnInit(): void {
    this.carregarItens();
    this.carregarTitulos();
  }

  // Helper pra criar item "vazio" que satisfaz a interface
  private criarItemVazio(): ItemInterface {
    return {
      id: 0,
      numSerie: 0,
      dtAquisicao: '',
      tipoItem: '',
      titulo: {} as TituloInterface,   // objeto vazio "forçado"
    };
  }

  carregarItens(): void {
    this.itemService.listar().subscribe({
      next: (dados) => this.itens.set(dados),
      error: (erro) => {
        console.error('Erro ao buscar itens:', erro);
        this.mensagemErro.set('Não foi possível carregar os itens.');
      }
    });
  }

  carregarTitulos(): void {
    this.tituloService.listar().subscribe({
      next: (dados) => this.titulos.set(dados),
      error: (erro) => console.error('Erro ao buscar títulos:', erro)
    });
  }

  abrirFormulario(): void {
    this.itemAtual = this.criarItemVazio();
    this.tituloIdSelecionado = null;
    this.mensagemErro.set('');
    this.mostrarFormulario.set(true);
  }

  cancelar(): void {
    this.mostrarFormulario.set(false);
    this.mensagemErro.set('');
  }

  salvarItem(): void {
  console.log('=== SALVAR ITEM ===');
  console.log('tituloIdSelecionado:', this.tituloIdSelecionado);

  if (!this.itemAtual.numSerie || this.itemAtual.numSerie <= 0) {
    this.mensagemErro.set('Informe um número de série válido.');
    return;
  }
  if (!this.itemAtual.dtAquisicao) {
    this.mensagemErro.set('Informe a data de aquisição.');
    return;
  }
  if (!this.itemAtual.tipoItem?.trim()) {
    this.mensagemErro.set('Selecione o tipo do item.');
    return;
  }
  if (!this.tituloIdSelecionado) {
    this.mensagemErro.set('Selecione um título.');
    return;
  }

  // ✅ PAYLOAD CORRETO - titulo sempre com { id: ... }
  const payload: any = {
    numSerie: this.itemAtual.numSerie,
    dtAquisicao: this.itemAtual.dtAquisicao,
    tipoItem: this.itemAtual.tipoItem,
    titulo: { id: this.tituloIdSelecionado },   // <-- AQUI é o pulo do gato
  };

  // Só inclui o id se for edição (id > 0)
  if (this.itemAtual.id && this.itemAtual.id > 0) {
    payload.id = this.itemAtual.id;
  }

  console.log('Payload:', JSON.stringify(payload));

  // Atualizar
  if (this.itemAtual.id && this.itemAtual.id > 0) {
    this.itemService.atualizar(this.itemAtual.id, payload).subscribe({
      next: (itemAtualizado) => {
        this.itens.update(lista =>
          lista.map(i => i.id === itemAtualizado.id ? itemAtualizado : i)
        );
        this.cancelar();
      },
      error: (erro) => {
        console.error('Erro ao atualizar item:', erro);
        this.mensagemErro.set('Não foi possível atualizar o item.');
      }
    });
    return;
  }

  // Criar
  this.itemService.salvar(payload).subscribe({
    next: (itemSalvo) => {
      this.itens.update(lista => [...lista, itemSalvo]);
      this.cancelar();
    },
    error: (erro) => {
      console.error('Erro ao salvar item:', erro);
      this.mensagemErro.set('Não foi possível salvar o item.');
    }
  });
}

  editarItem(id: number): void {
    this.itemService.buscarPorId(id).subscribe({
      next: (item) => {
        this.itemAtual = { ...item };
        this.tituloIdSelecionado = item.titulo?.id ?? null;
        this.mostrarFormulario.set(true);
      },
      error: (erro) => {
        console.error('Erro ao buscar item:', erro);
        this.mensagemErro.set('Não foi possível buscar o item.');
      }
    });
  }

  excluirItem(id: number): void {
    if (!confirm('Deseja realmente excluir este item?')) {
      return;
    }

    this.itemService.excluir(id).subscribe({
      next: () => this.itens.update(lista => lista.filter(i => i.id !== id)),
      error: (erro) => {
        console.error('Erro ao excluir item:', erro);
        this.mensagemErro.set('Não foi possível excluir o item.');
      }
    });
  }
}
