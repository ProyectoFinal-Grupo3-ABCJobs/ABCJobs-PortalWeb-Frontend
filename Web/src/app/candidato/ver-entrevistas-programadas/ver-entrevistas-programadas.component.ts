import { Component,OnInit  } from '@angular/core';
import { Entrevista } from '../entrevista';
import { CandidatoService } from '../candidato.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ver-entrevistas-programadas',
  templateUrl: './ver-entrevistas-programadas.component.html',
  styleUrls: ['./ver-entrevistas-programadas.component.css']
})
export class VerEntrevistasProgramadasComponent implements OnInit{

  entrevistas: Array<Entrevista> = [];
  candidatoId: number = 0 ;
  objetoJSON = ""
  datosEntrevista="";
  constructor(private candidatoService: CandidatoService,private router: Router) { }

  obtenerEntrevistas(){
    this.candidatoService.verEntrevistas()
    .subscribe((entrevistas) => {

      console.log("entrevistas",entrevistas)

      // this.entrevistas.push(entrevistas)
      // this.objetoJSON = JSON.stringify(this.entrevistas[0]);
      // this.datosEntrevista = JSON.parse(this.objetoJSON);
    });
  }

  obtenerPruebas(){
      this.candidatoService.verPruebas()
      .subscribe((pruebas) => {
  
        console.log("pruebas",pruebas)
  
        // this.entrevistas.push(entrevistas)
        // this.objetoJSON = JSON.stringify(this.entrevistas[0]);
        // this.datosEntrevista = JSON.parse(this.objetoJSON);
      });
  }
  ngOnInit() {
    //this.obtenerEntrevistas()
  }



}
