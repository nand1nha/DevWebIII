import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

interface AtorInterface {
  id: number;
  nome: string;
}

@Component({
  selector: 'app-ator',
  imports: [FormsModule, CommonModule],
  templateUrl: './ator.html',
  styleUrl: './ator.css',
})
export class Ator {
  atores: AtorInterface[] = [
    { id: 1, nome: 'Leonardo DiCaprio' }
  ];

  atorAtual: AtorInterface = { id: 0, nome: '' };
  mostrarFormulario = false;

  abrirFormulario(): void {
    this.atorAtual = { id: 0, nome: '' };
    this.mostrarFormulario = true;
  } 

  editarAtor(id: number): void {
    const encontrado = this.atores.find(a => a.id === id);
    if (encontrado) {
      this.atorAtual = { ...encontrado };
      this.mostrarFormulario = true;
    }
  }

  excluirAtor(id: number): void {
    this.atores = this.atores.filter(a => a.id !== id);
  }

  salvarAtor(): void {
    if (this.atorAtual.id === 0) {
      const novoId = this.atores.length
        ? Math.max(...this.atores.map(a => a.id)) + 1
        : 1;
      this.atores.push({ id: novoId, nome: this.atorAtual.nome });
    } else {
      const index = this.atores.findIndex(a => a.id === this.atorAtual.id);
      if (index > -1) this.atores[index] = { ...this.atorAtual };
    }
    this.cancelar();
  }

  cancelar(): void {
    this.mostrarFormulario = false;
    this.atorAtual = { id: 0, nome: '' };
  }

}
