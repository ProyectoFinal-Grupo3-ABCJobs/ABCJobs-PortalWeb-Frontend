import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Proyecto ABC Jobs';
  perfilUsuario;

  constructor(){

  }

  ngOnInit(){

      this.perfilUsuario = 'VISITANTE';
      
      // localStorage.setItem('token', '');
      // localStorage.setItem('perfil', ''); 


    
    
  }
}
