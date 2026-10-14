import { Component, OnInit, signal } from '@angular/core';
import { TituloInterface, TituloService } from '../../services/titulo-service';
import { AtorInterface, AtorService } from '../../services/ator-service';
import { ClasseInterface, ClasseService } from '../../services/classe-service';
import { DiretorInterface, DiretorService } from '../../services/diretor-service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-titulo',
  imports: [FormsModule, CommonModule],
  templateUrl: './titulo.html',
  styleUrl: './titulo.css',
})
export class Titulo implements OnInit{
  titulos = signal<TituloInterface[]>([]);
  atores = signal<AtorInterface[]>([]);
  diretores = signal<DiretorInterface[]>([]);
  classes = signal<ClasseInterface[]>([]);

  mostrarFormulario = signal(false);
  mensagemErro = signal('');

  tituloAtual: TituloInterface = this.criarTituloVazio();

  diretorIdSelecionado: number | null = null;
  classeIdSelecionado: number | null = null;
  atoresIdsSelecionados: number[] = [];

  constructor(
    private tituloService: TituloService,
    private atorService: AtorService,
    private diretorService: DiretorService,
    private classeService: ClasseService
  ) {}

  ngOnInit(): void {
    this.carregarTitulos();
    this.carregarAtores();
    this.carregarDiretores();
    this.carregarClasses();
  }

  // Cria um título "vazio" que satisfaz a interface obrigatória
  private criarTituloVazio(): TituloInterface {
    return {
      id: 0,
      nome: '',
      ano: new Date().getFullYear(),
      sinopse: '',
      categoria: '',
      diretor: { id: 0, nome: '' } as DiretorInterface,
      classe: { id: 0, nome: '' } as ClasseInterface,
      atores: [],
      itens: [],
    };
  }

  carregarTitulos(): void {
    this.tituloService.listar().subscribe({
      next: (dados) => this.titulos.set(dados),
      error: (erro) => {
        console.error('Erro ao buscar títulos:', erro);
        this.mensagemErro.set('Não foi possível carregar os títulos.');
      }
    });
  }

  carregarAtores(): void {
    this.atorService.listar().subscribe({
      next: (dados) => this.atores.set(dados),
      error: (erro) => console.error('Erro ao buscar atores:', erro)
    });
  }

  carregarDiretores(): void {
    this.diretorService.listar().subscribe({
      next: (dados) => this.diretores.set(dados),
      error: (erro) => console.error('Erro ao buscar diretores:', erro)
    });
  }

  carregarClasses(): void {
    this.classeService.listar().subscribe({
      next: (dados) => this.classes.set(dados),
      error: (erro) => console.error('Erro ao buscar classes:', erro)
    });
  }

  abrirFormulario(): void {
    this.tituloAtual = this.criarTituloVazio();
    this.diretorIdSelecionado = null;
    this.classeIdSelecionado = null;
    this.atoresIdsSelecionados = [];
    this.mensagemErro.set('');
    this.mostrarFormulario.set(true);
  }

  cancelar(): void {
    this.mostrarFormulario.set(false);
    this.mensagemErro.set('');
  }

  salvarTitulo(): void {
    if (!this.tituloAtual.nome?.trim()) {
      this.mensagemErro.set('O nome é obrigatório.');
      return;
    }

    // Monta o objeto com as relações no formato do back
    const payload: TituloInterface = {
      id: this.tituloAtual.id,
      nome: this.tituloAtual.nome,
      ano: this.tituloAtual.ano,
      sinopse: this.tituloAtual.sinopse,
      categoria: this.tituloAtual.categoria,
      diretor: { id: this.diretorIdSelecionado! } as DiretorInterface,
      classe: { id: this.classeIdSelecionado! } as ClasseInterface,
      atores: this.atoresIdsSelecionados.map(id => ({ id } as AtorInterface)),
      itens: [],
    };

    // O back faz a blindagem do id, mas limpamos aqui também
    if (!this.tituloAtual.id || this.tituloAtual.id === 0) {
      delete (payload as any).id;
    }

    if (this.tituloAtual.id && this.tituloAtual.id > 0) {
      this.tituloService.atualizar(this.tituloAtual.id, payload).subscribe({
        next: (tituloAtualizado) => {
          this.titulos.update(lista =>
            lista.map(t => t.id === tituloAtualizado.id ? tituloAtualizado : t)
          );
          this.cancelar();
        },
        error: (erro) => {
          console.error('Erro ao atualizar título:', erro);
          this.mensagemErro.set('Não foi possível atualizar o título.');
        }
      });
      return;
    }

    this.tituloService.salvar(payload).subscribe({
      next: (tituloSalvo) => {
        this.titulos.update(lista => [...lista, tituloSalvo]);
        this.cancelar();
      },
      error: (erro) => {
        console.error('Erro ao salvar título:', erro);
        this.mensagemErro.set('Não foi possível salvar o título.');
      }
    });
  }

  editarTitulo(id: number): void {
    this.tituloService.buscarPorId(id).subscribe({
      next: (titulo) => {
        this.tituloAtual = { ...titulo };
        this.diretorIdSelecionado = titulo.diretor?.id ?? null;
        this.classeIdSelecionado = titulo.classe?.id ?? null;
        this.atoresIdsSelecionados = titulo.atores?.map(a => a.id) ?? [];
        this.mostrarFormulario.set(true);
      },
      error: (erro) => {
        console.error('Erro ao buscar título:', erro);
        this.mensagemErro.set('Não foi possível buscar o título.');
      }
    });
  }

  excluirTitulo(id: number): void {
    if (!confirm('Deseja realmente excluir este título?')) return;

    this.tituloService.excluir(id).subscribe({
      next: () => this.titulos.update(lista => lista.filter(t => t.id !== id)),
      error: (erro) => {
        console.error('Erro ao excluir título:', erro);
        this.mensagemErro.set('Não foi possível excluir o título.');
      }
    });
  }

  toggleAtor(id: number): void {
    const idx = this.atoresIdsSelecionados.indexOf(id);
    if (idx >= 0) {
      this.atoresIdsSelecionados.splice(idx, 1);
    } else {
      this.atoresIdsSelecionados.push(id);
    }
  }

  isAtorSelecionado(id: number): boolean {
    return this.atoresIdsSelecionados.includes(id);
  }
}
