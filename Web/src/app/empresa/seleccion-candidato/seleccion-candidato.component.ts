import { Component, OnInit } from '@angular/core';
import { EmpresaService } from '../empresa.service';
import { Router } from '@angular/router';
import { Proyecto } from '../proyecto';
import { Contrato } from '../contrato';
import { ToastrService } from 'ngx-toastr';

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
  contrato: Array<Contrato> = []
  dicContrato = {}
  idPerfil=''
  constructor(private empresaService: EmpresaService,private router: Router,private toastr: ToastrService) { }
  

  ngOnInit() {
    this.obtenerProyectos()
  }

  obtenerProyectos(){
    this.empresaService.obtenerDataCandidatosAprobadosPorIdEmpresa()
    .subscribe((proyectos) => {
      console.log("Hole que estas mostrando", proyectos)
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
    this.idPerfil = '';

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
  contratarCandidato(){
    this.dicContrato = {
      numeroContrato:parseInt(this.numeroContrato),
      idCandidato:this.idCandidatoContratar,
      nombreCandidato:this.nombreCandidatoContratar,
      idEmpresa:0,
      idProyecto:this.listaPerfiles['idProyecto'],
      idCargo:0
    }
    // this.router.navigate(['/empresa/main']);


    // Paso 2: Cambiar estado candidato
    this.actualizarCandidato(this.idCandidatoContratar)

    // Paso 3: Eliminar candidato de entrevista
    this.eliminarCandidatoEntrevista(this.listaPerfiles['idProyecto'],this.idCandidatoContratar)

    // Paso 4: Eliminar tabla de emparejamiento
    this.eliminarCandidatoEmparejamiento(this.listaPerfiles['idProyecto'],this.idCandidatoContratar)
    this.router.navigate(['/empresa/main']);
        // Paso 1: Registrar contrato
    this.registrarContrato(this.dicContrato)
  }
  cancelar(){
    // console.log("Contratado")
    // window.location.reload();
    this.router.navigate(['/empresa/main']);
  }

  registrarContrato(contrato: any){
    this.empresaService.registrarContrato(contrato)
    .subscribe((contrato) => {
      this.showSuccess()
      // this.contrato = contrato;
      // console.log("valor de contrato es:", contrato)
    },
    error => {
      if (error.statusText === "CONFLICT") {
        this.showError(`Ya existe un proyecto con ese nombre`)
      } else {
        this.showError(`Ha ocurrido un error: ${error.message}`)
      }

    });
  }

  showError(error: string) {
    this.toastr.error(error, "Error")
  }

  showSuccess() {
    this.toastr.success(`Contrato registrado`, "Registro exitoso");
  }



  actualizarCandidato(idCandidato:string){
    this.empresaService.actualizarEstadoCandidato(idCandidato)
    .subscribe((candidato) => {
      // this.contrato = contrato;
      // console.log("valor de contrato es:", candidato)
    });
  }

  eliminarCandidatoEntrevista(idProyecto:string, idCandidato:string){
    this.empresaService.eliminarCandidatoEntrevista(idProyecto, idCandidato)
    .subscribe((candidato) => {
      // this.contrato = contrato;
      // console.log("valor de contrato es:", candidato)
    });
  }

  eliminarCandidatoEmparejamiento(idProyecto:string, idCandidato:string){
    this.empresaService.eliminarCandidatoEmparejamiento(idProyecto, idCandidato)
    .subscribe((candidato) => {
      // this.contrato = contrato;
      // console.log("valor de contrato es:", candidato)
    });
  }

}
