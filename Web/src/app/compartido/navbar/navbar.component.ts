import { Component, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { LoginService } from 'src/app/autenticacion/login/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{

    perfilUsuario;

    constructor(private servicioLogin: LoginService){

    }

    ngOnInit(){

      this.servicioLogin.perfil$.subscribe((perfilUsr) =>{
        this.perfilUsuario = perfilUsr
      })

      
      
    }

    


}
