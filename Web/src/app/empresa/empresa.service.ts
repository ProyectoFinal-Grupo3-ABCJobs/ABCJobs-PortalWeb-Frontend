import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Empresa } from './empresa';


@Injectable({
  providedIn: 'root'
})
export class EmpresaService {
  private backUrl: string = environment.apiUrl

  constructor(private http: HttpClient) { }

  crearEmpresa(empresa: Empresa): Observable<Empresa> {
    console.log("Empresa",empresa)
    console.log("direccion",`${this.backUrl}/company/register`)
    return this.http.post<Empresa>(`${this.backUrl}/company/register`, empresa)
  }

}
