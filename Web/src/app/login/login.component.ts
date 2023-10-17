import { Component } from '@angular/core';
import { JwtHelperService } from "@auth0/angular-jwt";
import { LoginService } from './login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  helper = new JwtHelperService();

  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }

  error: boolean = false

  ngOnInit() {
  }


  onLogInUsuario(usuario: string, contrasena: string) {
    this.error = false

    this.loginService.userLogIn(usuario, contrasena)
      .subscribe(res => {
        // const decodedToken = this.helper.decodeToken(res.token);
        // this.router.navigate([`/carreras/${decodedToken.sub}/${res.token}`])
        if (res.tipoUsuario==='CANDIDATO'){
          this.router.navigate([`candidato/main`])
        }
        if (res.tipoUsuario==='EMPRESA'){
          this.router.navigate([`empresa/main`])
        }
        if (res.tipoUsuario==='FUNCIONARIO'){
          this.router.navigate([`funcionario/main`])
        }

        // this.router.navigate([`/registro`])
        
      },
        error => {
          this.error = true
        })
  }

}
