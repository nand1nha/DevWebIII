import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AtorInterface } from './ator-service';
import { ClasseInterface } from './classe-service';
import { DiretorInterface } from './diretor-service';
import { ItemInterface } from './item-service';

export interface TituloInterface {
  id: number;
  nome: string;
  ano: number;
  sinopse: string;
  categoria: string;
  diretor: DiretorInterface;
  classe: ClasseInterface;
  atores: AtorInterface[];
  itens: ItemInterface[];
}

@Injectable({
  providedIn: 'root',
})
export class TituloService {
  private apiUrl = 'http://localhost:8080/api/titulos';

  constructor(private http: HttpClient) {}

  listar(): Observable<TituloInterface[]> {
    return this.http.get<TituloInterface[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<TituloInterface> {
    return this.http.get<TituloInterface>(`${this.apiUrl}/${id}`);
  }

  salvar(titulo: TituloInterface): Observable<TituloInterface> {
    return this.http.post<TituloInterface>(this.apiUrl, titulo);
  }

  atualizar(id: number, titulo: TituloInterface): Observable<TituloInterface> {
    return this.http.put<TituloInterface>(`${this.apiUrl}/${id}`, titulo);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
