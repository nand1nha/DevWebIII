import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ClasseService } from '../../services/classe-service';

interface ClasseInterface {
  id: number;
  nome: string;
  valor: number;
  prazoDevolucao: number;
}

@Component({
  selector: 'app-classe',
  imports: [FormsModule, CommonModule],
  templateUrl: './classe.html',
  styleUrl: './classe.css',
})
export class Classe implements OnInit{
  classes = signal<ClasseInterface[]>([]);

  mostrarFormulario = signal(false);

  classeAtual: ClasseInterface = { id: 0, nome: '', valor: 0, prazoDevolucao: 0 };

  mensagemErro = signal('');

  constructor(private classeService: ClasseService) {}

  ngOnInit(): void {
    this.carregarClasses();
  }

  carregarClasses(): void {
    this.classeService.listar().subscribe({
      next: (dados) => this.classes.set(dados),
      error: (erro) => { 
        console.error('Erro ao buscar classes:', erro),
        this.mensagemErro.set('Não foi possível salvar a classe. Tente novamente.');
      }
    });
  }

  abrirFormulario(): void {
    this.classeAtual = { id: 0, nome: '', valor: 0, prazoDevolucao: 0 };
    this.mostrarFormulario.set(true);
  }

  cancelar(): void {
    this.mostrarFormulario.set(false);
    this.classeAtual = { id: 0, nome: '', valor: 0, prazoDevolucao: 0 };
  }

  salvarClasse(): void {
    if (!this.classeAtual.nome?.trim()) {
      return;
    }

    if (this.classeAtual.id === 0) {
      this.classeService.salvar(this.classeAtual).subscribe({
        next: (classeSalvo) => {
          this.classes.update(lista => [...lista, classeSalvo]);
          this.cancelar();
        },
        error: (erro) => {
          console.error('Erro ao salvar classe:', erro),
          this.mensagemErro.set('Não foi possível salvar a classe. Tente novamente.');
        }
      });
      return;
    }

    this.classeService.atualizar(this.classeAtual.id, this.classeAtual).subscribe({
      next: (classeAtualizado) => {
        this.classes.update(lista =>
          lista.map(a => a.id === classeAtualizado.id ? classeAtualizado : a)
        );
        this.cancelar();
      },
      error: (erro) => {
        console.error('Erro ao atualizar classe:', erro),
        this.mensagemErro.set('Não foi possível salvar a classe. Tente novamente.');
      }
    });
  }

  editarClasse(id: number): void {
    this.classeService.buscarPorId(id).subscribe({
      next: (classe) => {
        this.classeAtual = { id: classe.id, nome: classe.nome, valor: classe.valor, prazoDevolucao: classe.prazoDevolucao };
        this.mostrarFormulario.set(true);
      },
      error: (erro) => {
        console.error('Erro ao buscar ator:', erro),
        this.mensagemErro.set('Não foi possível salvar o ator. Tente novamente.');
      }
    });
  }

  excluirClasse(id: number): void {
    if (!confirm('Deseja realmente excluir esta classe?')) {
      return;
    }

    this.classeService.excluir(id).subscribe({
      next: () => this.classes.update(lista => lista.filter(a => a.id !== id)),
      error: (erro) => {
        console.error('Erro ao excluir classe:', erro),
        this.mensagemErro.set('Não foi possível salvar a classe. Tente novamente.');
      }
    });
  }
}
