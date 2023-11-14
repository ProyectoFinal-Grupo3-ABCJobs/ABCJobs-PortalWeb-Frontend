import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Empresa } from './empresa';
import { Proyecto } from './proyecto';
import { EmpleadoInterno } from './empleadoInterno';
import { jwtDecode } from "jwt-decode";
import { Ficha } from './ficha';
import { Perfil } from './perfil';



@Injectable({
  providedIn: 'root'
})
export class EmpresaService {
  private backUrl: string = environment.apiUrl
  private idEmpresa: string = '';
  constructor(private http: HttpClient) { }

  crearEmpresa(empresa: Empresa): Observable<Empresa> {
    return this.http.post<Empresa>(`${this.backUrl}
    Juni`, empresa)
  }

  crearProyecto(proyecto: Proyecto): Observable<Proyecto> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post<Proyecto>(`${this.backUrl}5002/company/${this.idEmpresa}/projectCreate`, proyecto, { headers })
  }

  verProyectos(): Observable<Proyecto> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    // console.log('El token es: ', token)
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Proyecto>(`${this.backUrl}5002/company/${this.idEmpresa}/projects`, { headers })
  }

  verCandidatosEmparejadosPorIdProyecto(idProyecto: string): Observable<any> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    // console.log('El token es: ', token)
    if (token) {
      const decodedToken = jwtDecode(token);
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Proyecto>(`${this.backUrl}5002/company/motorEmparejamiento/proyectos/${idProyecto}`, { headers })
  }

  asignarEmpleado(empleadoInterno: EmpleadoInterno): Observable<EmpleadoInterno> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post<EmpleadoInterno>(`${this.backUrl}5002/company/${this.idEmpresa}/assignEmployee`, empleadoInterno, { headers })
  }

  verEmpleadosInternos(): Observable<EmpleadoInterno> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<EmpleadoInterno>(`${this.backUrl}5002/company/${this.idEmpresa}/internalEmployees`, { headers })
  }

  verPerfiles(idProyecto: number): Observable<Perfil> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Perfil>(`${this.backUrl}5002/company/projects/${idProyecto}/profiles`, { headers })
  }

  crearFicha(ficha: Ficha): Observable<Ficha> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post<Ficha>(`${this.backUrl}5002/company/projects/${ficha.idProyecto}/files`, ficha, { headers })
  }
}
