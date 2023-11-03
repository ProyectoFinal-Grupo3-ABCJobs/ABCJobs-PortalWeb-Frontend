import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/autenticacion/login/login.service';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent implements OnInit{
  datosUsuario:string;

  constructor(private servicioLogin: LoginService){ }

  ngOnInit(){

    this.servicioLogin.usr$.subscribe((datosUsr) =>{
     
      this.datosUsuario = datosUsr
    })

    
    
  }
}
