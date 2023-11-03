import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private backUrl: string = environment.apiUrl;
 
  private perfilActual = new BehaviorSubject<string>('VISITANTE');
  perfil$ = this.perfilActual.asObservable();

  private usuarioActual = new BehaviorSubject<string>('ABC JOBS');
  usr$ = this.usuarioActual.asObservable();

  constructor(private http: HttpClient) { }

  userLogIn(usuario: string, contrasena: string): Observable<any> {

      return this.http.post<any>(`${this.backUrl}5000/users/auth`, { "usuario": usuario, "contrasena": contrasena });
  }

  perfilUsuario(perfilUsuario:string){
    this.perfilActual.next(perfilUsuario)
  }


  datoUsuario(usrDatos:string){
    this.usuarioActual.next(usrDatos)
  }

}
