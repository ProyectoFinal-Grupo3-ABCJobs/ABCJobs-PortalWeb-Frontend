import { Component, OnInit } from '@angular/core';
import { EmpresaService } from '../empresa.service';
import { Router } from '@angular/router';
import { Proyecto } from '../proyecto';

@Component({
  selector: 'app-seleccion-candidato',
  templateUrl: './seleccion-candidato.component.html',
  styleUrls: ['./seleccion-candidato.component.css']
})
export class SeleccionCandidatoComponent implements OnInit{
  mostrarPuntero = false;
  proyectos: Array<any> = [];
  listaPerfiles= "";
  itemSeleccionado: any;
  idCandidatoContratar:""
  nombreCandidatoContratar: "";
  nombreProyecto: "";
  perfilCandidatoContratar: any;
  candidato:any;
  numeroContrato: string;
  constructor(private empresaService: EmpresaService,private router: Router) { }
  

  ngOnInit() {
    this.obtenerProyectos()
  }

  obtenerProyectos(){
    this.empresaService.obtenerDataCandidatosAprobadosPorIdEmpresa()
    .subscribe((proyectos) => {
      this.proyectos = proyectos;

    });
  }


  cambiarEstadoPuntero(estado: boolean): void {
    this.mostrarPuntero = estado;
  }

  listarPerfilesCandidatos(proyecto:string,index:number){

    this.numeroContrato = '';
    this.nombreCandidatoContratar = '';
    this.idCandidatoContratar = '';
    this.perfilCandidatoContratar = '';
    this.nombreProyecto = '';


    this.itemSeleccionado = index;    
    this.listaPerfiles=proyecto
    // console.log("Los perfiles son: ",this.listaPerfiles)
  }

  candidatoSeleccionado(candidato:string, perfil:string){
    this.numeroContrato = Math.floor(Math.random() * 1000).toString();
    this.nombreCandidatoContratar = candidato['nombreCandidato']
    this.idCandidatoContratar = candidato['idCandidato']
    this.perfilCandidatoContratar = perfil
    this.nombreProyecto = this.listaPerfiles['proyectoNombre']


  }
  irAMain(){
    console.log("Contratado")
    // this.router.navigate(['/empresa/main']);
  }
}
