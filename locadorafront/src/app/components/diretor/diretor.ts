import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DiretorInterface, DiretorService } from '../../services/diretor-service';

@Component({
  selector: 'app-diretor',
  imports: [FormsModule, CommonModule],
  templateUrl: './diretor.html',
  styleUrl: './diretor.css',
})
export class Diretor {
  diretores = signal<DiretorInterface[]>([]);

  mostrarFormulario = signal(false);

  diretorAtual: DiretorInterface = { id: 0, nome: '' };

  mensagemErro = signal('');

  constructor(private diretorService: DiretorService) {}

  ngOnInit(): void {
    this.carregarDiretores();
  }

  carregarDiretores(): void {
    this.diretorService.listar().subscribe({
      next: (dados) => this.diretores.set(dados),
      error: (erro) => { 
        console.error('Erro ao buscar diretores:', erro),
        this.mensagemErro.set('Não foi possível salvar o diretor. Tente novamente.');
      }
    });
  }

  abrirFormulario(): void {
    this.diretorAtual = { id: 0, nome: '' };
    this.mostrarFormulario.set(true);
  }

  cancelar(): void {
    this.mostrarFormulario.set(false);
    this.diretorAtual = { id: 0, nome: '' };
  }

  salvarDiretor(): void {
    if (!this.diretorAtual.nome?.trim()) {
      return;
    }

    if (this.diretorAtual.id === 0) {
      this.diretorService.salvar(this.diretorAtual).subscribe({
        next: (diretorSalvo) => {
          this.diretores.update(lista => [...lista, diretorSalvo]);
          this.cancelar();
        },
        error: (erro) => {
          console.error('Erro ao salvar diretor:', erro),
          this.mensagemErro.set('Não foi possível salvar o diretor. Tente novamente.');
        }
      });
      return;
    }

    this.diretorService.atualizar(this.diretorAtual.id, this.diretorAtual).subscribe({
      next: (diretorAtualizado) => {
        this.diretores.update(lista =>
          lista.map(a => a.id === diretorAtualizado.id ? diretorAtualizado : a)
        );
        this.cancelar();
      },
      error: (erro) => {
        console.error('Erro ao atualizar diretor:', erro),
        this.mensagemErro.set('Não foi possível salvar o diretor. Tente novamente.');
      }
    });
  }

  editarDiretor(id: number): void {
    this.diretorService.buscarPorId(id).subscribe({
      next: (diretor) => {
        this.diretorAtual = { id: diretor.id, nome: diretor.nome };
        this.mostrarFormulario.set(true);
      },
      error: (erro) => {
        console.error('Erro ao buscar ator:', erro),
        this.mensagemErro.set('Não foi possível salvar o ator. Tente novamente.');
      }
    });
  }

  excluirDiretor(id: number): void {
    if (!confirm('Deseja realmente excluir este diretor?')) {
      return;
    }

    this.diretorService.excluir(id).subscribe({
      next: () => this.diretores.update(lista => lista.filter(a => a.id !== id)),
      error: (erro) => {
        console.error('Erro ao excluir diretor:', erro),
        this.mensagemErro.set('Não foi possível salvar o diretor. Tente novamente.');
      }
    });
  }
}
