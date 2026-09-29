import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

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
export class Classe {
  classes: ClasseInterface[] = [
    { id: 1, nome: 'Ação', valor: 10, prazoDevolucao: 5 }
  ];

  classeAtual: ClasseInterface = { id: 0, nome: '', valor: 0, prazoDevolucao: 0 };
  mostrarFormulario = false;
  
  abrirFormulario(): void {
    this.classeAtual = { id: 0, nome: '', valor: 0, prazoDevolucao: 0 };
    this.mostrarFormulario = true;
  }

  editarClasse(id: number): void {
    const encontrado = this.classes.find(c => c.id === id);
    if (encontrado) {
      this.classeAtual = { ...encontrado };
      this.mostrarFormulario = true;
    }
  }

  excluirClasse(id: number): void {
    this.classes = this.classes.filter(c => c.id !== id);
  }

  salvarClasse(): void {
    if (this.classeAtual.id === 0) {
      const novoId = this.classes.length
        ? Math.max(...this.classes.map(c => c.id)) + 1
        : 1;
      this.classes.push({ ...this.classeAtual, id: novoId });
    } else {
      const index = this.classes.findIndex(c => c.id === this.classeAtual.id);
      if (index > -1) this.classes[index] = { ...this.classeAtual };
    }
    this.cancelar();
  }

  cancelar(): void {
    this.mostrarFormulario = false;
    this.classeAtual = { id: 0, nome: '', valor: 0, prazoDevolucao: 0 };
  }
}
