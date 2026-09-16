import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-ator',
  imports: [FormsModule],
  templateUrl: './ator.html',
  styleUrl: './ator.css',
})
export class Ator {
  nome: string = '';

  salvar() {
    console.log('Nome do ator:', this.nome);

    alert('Ator cadastrado com sucesso!');

    this.nome = '';
  }
}
