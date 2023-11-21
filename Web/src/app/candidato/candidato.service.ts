import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { jwtDecode } from "jwt-decode";
import { Candidato } from './candidato';
import { Usuario } from './usuario';
import { Entrevista } from './entrevista';
import { Prueba } from './prueba';


@Injectable({
  providedIn: 'root'
})
export class CandidatoService {
  private backUrl: string = environment.apiUrl;
  private idCandidato: string = '';

  constructor(private http: HttpClient) { }

  registrarInfoCandidato(candidato: Candidato): Observable<Candidato> {
    console.log("candidato", candidato)
    return this.http.post<Candidato>(`${this.backUrl}5001/candidate/registerInfo`, candidato)
  }

  registrarUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.backUrl}5000/users/register`, usuario)
  }

  verEntrevistas(): Observable<Entrevista[]> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    // console.log('El token es: ', token)
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idCandidato = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Entrevista[]>(`${this.backUrl}5003/test/candidate/${this.idCandidato}/interviews`, { headers })
  }

  verPruebas(): Observable<Prueba[]> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idCandidato = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Prueba[]>(`${this.backUrl}5003/test/candidate/${this.idCandidato}`, { headers: headers })
  }

  

}
