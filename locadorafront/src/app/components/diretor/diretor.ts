import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

interface DiretorInterface {
  id: number;
  nome: string;
}

@Component({
  selector: 'app-diretor',
  imports: [FormsModule, CommonModule],
  templateUrl: './diretor.html',
  styleUrl: './diretor.css',
})
export class Diretor {
  diretores: DiretorInterface[] = [
    { id: 1, nome: 'Christopher Nolan' }
  ];

  diretorAtual: DiretorInterface = { id: 0, nome: '' };
  mostrarFormulario = false;
  
  abrirFormulario(): void {
    this.diretorAtual = { id: 0, nome: '' };
    this.mostrarFormulario = true;
  }

  editarDiretor(id: number): void {
    const encontrado = this.diretores.find(d => d.id === id);
    if (encontrado) {
      this.diretorAtual = { ...encontrado };
      this.mostrarFormulario = true;
    }
  }

  excluirDiretor(id: number): void {
    this.diretores = this.diretores.filter(d => d.id !== id);
  }

  salvarDiretor(): void {
    if (this.diretorAtual.id === 0) {
      const novoId = this.diretores.length
        ? Math.max(...this.diretores.map(d => d.id)) + 1
        : 1;
      this.diretores.push({ ...this.diretorAtual, id: novoId });
    } else {
      const index = this.diretores.findIndex(d => d.id === this.diretorAtual.id);
      if (index > -1) this.diretores[index] = { ...this.diretorAtual };
    }
    this.cancelar();
  }

  cancelar(): void {
    this.mostrarFormulario = false;
    this.diretorAtual = { id: 0, nome: '' };
  }
}
