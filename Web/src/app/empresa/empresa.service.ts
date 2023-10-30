import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Empresa } from './empresa';
import { Proyecto } from './proyecto';
import { jwtDecode } from "jwt-decode";



@Injectable({
  providedIn: 'root'
})
export class EmpresaService {
  private backUrl: string = environment.apiUrl

  constructor(private http: HttpClient) { }

  crearEmpresa(empresa: Empresa): Observable<Empresa> {
    return this.http.post<Empresa>(`${this.backUrl}5002/company/register`, empresa)
  }

  crearProyecto(proyecto: Proyecto): Observable<Proyecto> {
    return this.http.post<Proyecto>(`${this.backUrl}5002/company/projects/<int:id_empresa>`, proyecto)
  }

  verProyectos(empresa_id:number): Observable<Proyecto> {
    this.obtenerIdEmpresa()
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Proyecto>(`${this.backUrl}5002/company/projects/${empresa_id}`, {headers})
  }

  obtenerIdEmpresa(): any{

    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    if(token){  
      const decodedToken = jwtDecode(token);
      console.log(decodedToken);
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      //return this.http.get<Proyecto>(`${this.backUrl}5002/company/projects/`, {headers})
    }
    //return this.http.get<Proyecto>(`${this.backUrl}5002/company/projects/`)

  }

}
