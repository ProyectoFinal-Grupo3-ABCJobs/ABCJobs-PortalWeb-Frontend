import { Component, OnInit } from '@angular/core';
import { EmpresaService } from '../empresa.service';
import { Proyecto } from '../proyecto';
import { Router } from '@angular/router';
import { Candidato } from 'src/app/candidato/candidato';

@Component({
  selector: 'app-ver-emparejamiento',
  templateUrl: './ver-emparejamiento.component.html',
  styleUrls: ['./ver-emparejamiento.component.css']
})
export class VerEmparejamientoComponent implements OnInit{
  mostrarPuntero = false;
  proyectos: Array<Proyecto> = [];
  candidatosEmparejados: any;
  empresaId: number = 0 ;
  objetoJSON = "";
  datosProyecto="";
  datosCandidato=""
  itemSeleccionado: any; 
  constructor(private empresaService: EmpresaService,private router: Router) { }

  obtenerProyectos(){
    this.empresaService.verProyectos()
    .subscribe((proyectos) => {

      this.proyectos.push(proyectos)
      this.objetoJSON = JSON.stringify(this.proyectos[0]);
      this.datosProyecto = JSON.parse(this.objetoJSON);
    });
  }
  ngOnInit() {
    this.obtenerProyectos()
  }

  irAMain(){
    this.router.navigate(['/empresa/main']);
  }

  listarCandidatos(proyecto:string,index:number){
    this.itemSeleccionado = index;
    this.empresaService.verCandidatosEmparejadosPorIdProyecto(proyecto['idProyecto'])
    .subscribe((candidatos) => {

      if(!candidatos['Mensaje 200']){
        this.candidatosEmparejados = candidatos;
      }else{
        this.candidatosEmparejados= [];
      }
     

    });
  }

  cambiarEstadoPuntero(estado: boolean): void {
    this.mostrarPuntero = estado;
  }
}
