import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface AtorInterface {
  id: number;
  nome: string;
}

@Injectable({
  providedIn: 'root',
})
export class AtorService {
  private apiUrl = 'http://localhost:8080/api/atores';

  constructor(private http: HttpClient) {}

  listar(): Observable<AtorInterface[]> {
    return this.http.get<AtorInterface[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<AtorInterface> {
    return this.http.get<AtorInterface>(`${this.apiUrl}/${id}`);
  }

  salvar(ator: AtorInterface): Observable<AtorInterface> {
    return this.http.post<AtorInterface>(this.apiUrl, ator);
  }

  atualizar(id: number, ator: AtorInterface): Observable<AtorInterface> {
    return this.http.put<AtorInterface>(`${this.apiUrl}/${id}`, ator);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
