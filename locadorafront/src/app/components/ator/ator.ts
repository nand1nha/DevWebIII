import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AtorInterface, AtorService } from '../../services/ator-service';

@Component({
  selector: 'app-ator',
  imports: [FormsModule, CommonModule],
  templateUrl: './ator.html',
  styleUrl: './ator.css',
})
export class Ator implements OnInit{

  atores = signal<AtorInterface[]>([]);

  mostrarFormulario = signal(false);

  atorAtual: AtorInterface = { id: 0, nome: '' };

  mensagemErro = signal('');

  constructor(private atorService: AtorService) {}

  ngOnInit(): void {
    this.carregarAtores();
  }

  carregarAtores(): void {
    this.atorService.listar().subscribe({
      next: (dados) => this.atores.set(dados),
      error: (erro) => { 
        console.error('Erro ao buscar atores:', erro),
        this.mensagemErro.set('Não foi possível salvar o ator. Tente novamente.');
      }
    });
  }

  abrirFormulario(): void {
    this.atorAtual = { id: 0, nome: '' };
    this.mostrarFormulario.set(true);
  }

  cancelar(): void {
    this.mostrarFormulario.set(false);
    this.atorAtual = { id: 0, nome: '' };
  }

  salvarAtor(): void {
    if (!this.atorAtual.nome?.trim()) {
      return;
    }

    if (this.atorAtual.id === 0) {
      this.atorService.salvar(this.atorAtual).subscribe({
        next: (atorSalvo) => {
          this.atores.update(lista => [...lista, atorSalvo]);
          this.cancelar();
        },
        error: (erro) => {
          console.error('Erro ao salvar ator:', erro),
          this.mensagemErro.set('Não foi possível salvar o ator. Tente novamente.');
        }
      });
      return;
    }

    this.atorService.atualizar(this.atorAtual.id, this.atorAtual).subscribe({
      next: (atorAtualizado) => {
        this.atores.update(lista =>
          lista.map(a => a.id === atorAtualizado.id ? atorAtualizado : a)
        );
        this.cancelar();
      },
      error: (erro) => {
        console.error('Erro ao atualizar ator:', erro),
        this.mensagemErro.set('Não foi possível salvar o ator. Tente novamente.');
      }
    });
  }

  editarAtor(id: number): void {
    this.atorService.buscarPorId(id).subscribe({
      next: (ator) => {
        this.atorAtual = { id: ator.id, nome: ator.nome };
        this.mostrarFormulario.set(true);
      },
      error: (erro) => {
        console.error('Erro ao buscar ator:', erro),
        this.mensagemErro.set('Não foi possível salvar o ator. Tente novamente.');
      }
    });
  }

  excluirAtor(id: number): void {
    if (!confirm('Deseja realmente excluir este ator?')) {
      return;
    }

    this.atorService.excluir(id).subscribe({
      next: () => this.atores.update(lista => lista.filter(a => a.id !== id)),
      error: (erro) => {
        console.error('Erro ao excluir ator:', erro),
        this.mensagemErro.set('Não foi possível salvar o ator. Tente novamente.');
      }
    });
  }
}
