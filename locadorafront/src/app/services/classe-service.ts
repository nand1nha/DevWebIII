import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface ClasseInterface {
  id: number;
  nome: string;
  valor: number;
  prazoDevolucao: number;
}

@Injectable({
  providedIn: 'root',
})
export class ClasseService {
  private apiUrl = 'http://localhost:8080/api/classes';

  constructor(private http: HttpClient) {}

  listar(): Observable<ClasseInterface[]> {
    return this.http.get<ClasseInterface[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<ClasseInterface> {
    return this.http.get<ClasseInterface>(`${this.apiUrl}/${id}`);
  }

  salvar(classe: ClasseInterface): Observable<ClasseInterface> {
    return this.http.post<ClasseInterface>(this.apiUrl, classe);
  }

  atualizar(id: number, classe: ClasseInterface): Observable<ClasseInterface> {
    return this.http.put<ClasseInterface>(`${this.apiUrl}/${id}`, classe);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
