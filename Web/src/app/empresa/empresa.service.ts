import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Empresa } from './empresa';
import { Proyecto } from './proyecto';


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
    return this.http.post<Proyecto>(`${this.backUrl}5002/company/proyectos/<int:id_empresa>`, proyecto)
  }

}
