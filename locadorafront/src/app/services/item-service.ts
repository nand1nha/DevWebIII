import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TituloInterface } from './titulo-service';

export interface ItemInterface {
  id: number;
  numSerie: number;
  dtAquisicao: string;
  tipoItem: string;
  titulo: TituloInterface;
}

@Injectable({
  providedIn: 'root',
})
export class ItemService {
  private apiUrl = 'http://localhost:8080/api/itens';

  constructor(private http: HttpClient) {}

  listar(): Observable<ItemInterface[]> {
    return this.http.get<ItemInterface[]>(this.apiUrl);
  }

  listarPorTitulo(tituloId: number): Observable<ItemInterface[]> {
    return this.http.get<ItemInterface[]>(`${this.apiUrl}/titulo/${tituloId}`);
  }

  buscarPorId(id: number): Observable<ItemInterface> {
    return this.http.get<ItemInterface>(`${this.apiUrl}/${id}`);
  }

  salvar(item: ItemInterface): Observable<ItemInterface> {
    return this.http.post<ItemInterface>(this.apiUrl, item);
  }

  atualizar(id: number, item: ItemInterface): Observable<ItemInterface> {
    return this.http.put<ItemInterface>(`${this.apiUrl}/${id}`, item);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
