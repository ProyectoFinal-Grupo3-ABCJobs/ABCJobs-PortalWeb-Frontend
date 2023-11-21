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
import { Entrevista } from '../candidato/entrevista';
import { Contrato } from './contrato';
import { Candidato } from '../candidato/candidato';



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

  verProyectosSinFicha(): Observable<Proyecto> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    // console.log('El token es: ', token)
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Proyecto>(`${this.backUrl}5002/company/${this.idEmpresa}/projects/ficha`, { headers })
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

  verCandidatosAprobadosPorIdEmpresa(idEmpresa: string): Observable<any> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    // console.log('El token es: ', token)
    if (token) {
      const decodedToken = jwtDecode(token);
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Entrevista>(`${this.backUrl}5003/test/company/${idEmpresa}/interviews`, { headers })
  }

  obtenerDataCandidatosAprobadosPorIdEmpresa(): Observable<any> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    // console.log('El token es: ', token)
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Entrevista>(`${this.backUrl}5003/test/company/${this.idEmpresa}/interviews`, { headers })
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

  verTodosPerfiles(): Observable<Perfil> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Perfil>(`${this.backUrl}5002/company/profiles`, { headers })
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

  ejecutarMotorEmparejamiento(): Observable<any> {
    // const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    // console.log("El token es Gct: ",token)
    // if (token) {
      // const decodedToken = jwtDecode(token);
    // }
    // const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);    
    // return this.http.post<any>(`${this.backUrl}5002/company/motorEmparejamientoTempFicha`, { headers })
    return this.http.post<any>(`${this.backUrl}5002/company/motorEmparejamientoTempFicha`,Headers)
  }

  registrarContrato(contrato: Contrato): Observable<Contrato> {
    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    contrato['idEmpresa'] = parseInt(this.idEmpresa)
    return this.http.post<Contrato>(`${this.backUrl}5002/company/contratoCandidato`, contrato, { headers })
  }

  actualizarEstadoCandidato(candidato: string): Observable<Candidato> {

    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put<Candidato>(`${this.backUrl}5001/candidate/isHired/${candidato}`,candidato, { headers })
  }

  eliminarCandidatoEntrevista(idProyecto: string, idCandidato:string): Observable<any> {

    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.delete<any>(`${this.backUrl}5003/test/proyectos/${idProyecto}/candidatos/${idCandidato}/empresas/${this.idEmpresa}`,{ headers })

  }

  eliminarCandidatoEmparejamiento(idProyecto: string, idCandidato:string): Observable<any> {

    const token = localStorage.getItem('token'); // Obtener el token JWT de localStorage
    if (token) {
      const decodedToken = jwtDecode(token);
      this.idEmpresa = decodedToken['sub']['idEmpCanFunc'];
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.delete<any>(`${this.backUrl}5002/company/motorEmparejamiento/proyectos/${idProyecto}/candidatos/${idCandidato}`,{ headers })

  }


}
