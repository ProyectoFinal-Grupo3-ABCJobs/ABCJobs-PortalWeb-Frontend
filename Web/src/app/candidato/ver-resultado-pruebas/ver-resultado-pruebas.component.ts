import { Component, OnInit } from '@angular/core';
import { Prueba } from '../prueba';
import { CandidatoService } from '../candidato.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ver-resultado-pruebas',
  templateUrl: './ver-resultado-pruebas.component.html',
  styleUrls: ['./ver-resultado-pruebas.component.css']
})
export class VerResultadoPruebasComponent implements OnInit {

  pruebas: Array<Prueba> = [];
  candidatoId: number = 0 ;
  objetoJSON = ""
  constructor(private candidatoService: CandidatoService,private router: Router) { }

  obtenerpruebas(){
    this.candidatoService.verPruebas()
    .subscribe((pruebas) => {

      console.log("pruebas",pruebas)
    });
  }
  ngOnInit() {
    this.obtenerpruebas()
  }


}
