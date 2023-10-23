import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private backUrl: string = environment.apiUrl

  constructor(private http: HttpClient) { }

  userLogIn(usuario: string, contrasena: string): Observable<any> {
      return this.http.post<any>(`${this.backUrl}5000/users/auth`, { "usuario": usuario, "contrasena": contrasena });
  }



}
