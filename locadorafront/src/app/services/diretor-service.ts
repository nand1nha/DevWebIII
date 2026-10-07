import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface DiretorInterface {
  id: number;
  nome: string;
}

@Injectable({
  providedIn: 'root',
})
export class DiretorService {
  private apiUrl = 'http://localhost:8080/api/diretores';

  constructor(private http: HttpClient) {}

  listar(): Observable<DiretorInterface[]> {
    return this.http.get<DiretorInterface[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<DiretorInterface> {
    return this.http.get<DiretorInterface>(`${this.apiUrl}/${id}`);
  }

  salvar(diretor: DiretorInterface): Observable<DiretorInterface> {
    return this.http.post<DiretorInterface>(this.apiUrl, diretor);
  }

  atualizar(id: number, diretor: DiretorInterface): Observable<DiretorInterface> {
    return this.http.put<DiretorInterface>(`${this.apiUrl}/${id}`, diretor);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
