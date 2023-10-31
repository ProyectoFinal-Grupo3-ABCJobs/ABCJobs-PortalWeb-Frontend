import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/autenticacion/login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'gestion_autenticacion';


  perfilUsuario;

  constructor(){

  }

  ngOnInit(){

      this.perfilUsuario = 'VISITANTE'

    
    
  }
}
