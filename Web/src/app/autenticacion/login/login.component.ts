import { Component,OnInit } from '@angular/core';
import { JwtHelperService } from "@auth0/angular-jwt";
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  helper = new JwtHelperService();
  loginForm: FormGroup = new FormGroup({});

  constructor(
    private loginService: LoginService,
    private router: Router,
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
  ) { }

  error: boolean = false

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      usuario: ["", [Validators.required, Validators.maxLength(50)]],
      contrasena: ["", [Validators.required, Validators.maxLength(90), Validators.minLength(5)]],

      
    })
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

        
      },
        error => {

          this.error = true
        })
  }

}
