import { Component,OnInit  } from '@angular/core';
import { Entrevista } from '../entrevista';
import { Prueba } from '../prueba';
import { CandidatoService } from '../candidato.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ver-entrevistas-programadas',
  templateUrl: './ver-entrevistas-programadas.component.html',
  styleUrls: ['./ver-entrevistas-programadas.component.css']
})
export class VerEntrevistasProgramadasComponent implements OnInit{

  entrevistas: Array<Entrevista> = [];
  listaEntrevistas = [];
  candidatoId: number = 0 ;
  pruebas: Array<Prueba> = [];
  listaPruebas = [];
  pruebaHoy = false;


  constructor(private candidatoService: CandidatoService,private router: Router) { }

  obtenerEntrevistas(){
    this.candidatoService.verEntrevistas()
    .subscribe((entrevistas) => {

      
      this.entrevistas = entrevistas
      this.entrevistas.forEach(element => {
        if(!element.estado){
          this.listaEntrevistas.push(element)
        }
        
      });
      console.log("entrevistas",this.listaEntrevistas)
    });
  }
  obtenerPruebas(){
      this.candidatoService.verPruebas()
      .subscribe((pruebas) => {
  
        console.log("pruebas",pruebas)
        this.pruebas = pruebas
        this.pruebas.forEach(element => {
          const existe = this.listaPruebas.some(e => e.idProyecto === element.idProyecto);

          if(!element.estado && !existe){
            this.listaPruebas.push(element)
          }

          
        });
        console.log("pruebas lista ",this.listaPruebas)



      });

  }
  ngOnInit() {
    this.obtenerEntrevistas()
    this.obtenerPruebas()
  }



}
