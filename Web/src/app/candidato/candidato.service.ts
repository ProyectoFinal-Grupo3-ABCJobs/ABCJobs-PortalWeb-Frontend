import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Candidato } from './candidato';


@Injectable({
  providedIn: 'root'
})
export class CandidatoService {
  private backUrl: string = environment.apiUrl

  constructor(private http: HttpClient) { }

  registrarInfoCandidato(candidato: Candidato): Observable<Candidato> {
    return this.http.post<Candidato>(`${this.backUrl}/candidate/registerInfo`, candidato)
  }

}
