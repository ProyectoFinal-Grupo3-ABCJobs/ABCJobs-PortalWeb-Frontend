import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private backUrl: string = environment.apiUrl
  private perfilActual = new BehaviorSubject<string>('VISITANTE');
  perfil$ = this.perfilActual.asObservable();

  constructor(private http: HttpClient) { }

  userLogIn(usuario: string, contrasena: string): Observable<any> {

      return this.http.post<any>(`${this.backUrl}5000/users/auth`, { "usuario": usuario, "contrasena": contrasena });
  }

  perfilUsuario(perfilUsuario:string){
    
    this.perfilActual.next(perfilUsuario)

  }



}
