import { Component, OnInit } from '@angular/core';
import { EmpresaService } from '../empresa.service';
import { Router } from '@angular/router';
import { Proyecto } from '../proyecto';
import { ToastrService } from 'ngx-toastr';
import { NgFor } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registro-res-prueba-tecnica',
  templateUrl: './registro-res-prueba-tecnica.component.html',
  styleUrls: ['./registro-res-prueba-tecnica.component.css']
})
export class RegistroResPruebaTecnicaComponent implements OnInit{
  formulario: boolean = false;
  registroResultadoPruebaForm: FormGroup = new FormGroup({});
  mostrarPuntero = false;
  proyectos: Array<Proyecto> = [];
  proyecto: Array<Proyecto> = [];
  empresaId: number = 0 ;
  objetoJSON = "";
  objetoJSONCandidatos = ""
  datosProyecto="";
  datosCandidatos="";
  listaCandidatos : any;
  listaTotalCandidatos : any[] = [];
  infoCandidato: [];
  nombreCandidato: string;
  infoProyecto: [];
  infoPerfil: [];

  // constructor(private empresaService: EmpresaService,private router: Router) { }
  constructor(
    private empresaService: EmpresaService,
    private routerPath: Router,
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
  ) { }

  obtenerProyectos(){
    this.empresaService.verProyectos()
    .subscribe((proyectos) => {
      
      this.proyectos.push(proyectos)
      this.objetoJSON = JSON.stringify(this.proyectos[0]);
      this.datosProyecto = JSON.parse(this.objetoJSON);
    });
  }

  listarCandidatosPerfil(proyecto:any) {
    this.listaTotalCandidatos = []
    this.infoProyecto=proyecto
    this.empresaService.verCadidatosPorPerfil(proyecto.idProyecto)
      .subscribe((resultado) => {
        // console.log("El resultado es; ", resultado)
        this.listaCandidatos = resultado;
        for(let candidato of this.listaCandidatos){
          // console.log("El candidato es: ",candidato)
          this.listaTotalCandidatos.push(candidato)
        }

        // this.listaCandidatos.push(resultado)
        // this.objetoJSONCandidatos = JSON.stringify(this.listaCandidatos[0]);
        // this.datosCandidatos = JSON.parse(this.objetoJSONCandidatos);
      },
        error => {
          if (error.message.toString().includes("404")) {
            this.showError(`No hay candidatos a evaluar`)
          } else {
            this.showError(`Ha ocurrido un error: ${error.message}`)
          }
        })
  }

  cambiarEstadoPuntero(estado: boolean): void {
    this.mostrarPuntero = estado;
  }


  ngOnInit() {
    this.registroResultadoPruebaForm = this.formBuilder.group({
      resultado: ["", [Validators.required, Validators.maxLength(100)]],
      observaciones: ["", [Validators.required, Validators.maxLength(1000),]],
      aprobado: ["", [Validators.required]],
    })
    this.obtenerProyectos()

  }

  cancelCreate() {
    this.routerPath.navigate([`empresa/main`])
  }

  showError(error: string) {
    this.toastr.error(error, "Error")
  }

  seleccionCandidato(candidato,perfil){
    this.formulario = true;
    this.infoCandidato = candidato
    this.nombreCandidato = this.infoCandidato['candidatoNombre']
    this.infoPerfil = perfil

  }

  mostrarFormulario() {
    this.formulario = true;

  }
  showSuccess() {
    this.toastr.success(`Se ha registrado exitosamente`, "Registro exitoso");
  }

  registrarResultadoPruebaCandidato(evaluacion:any){
    console.log("El proyecto es:",this.infoCandidato)
    console.log("El id del proyecto es:",this.infoProyecto['idProyecto'] )
    console.log("El id del candidato es:",this.infoCandidato['idCandidato'] )
    console.log("nombre candidato:",this.nombreCandidato)
    console.log("El id dle perfil es: ", this.infoPerfil['idPerfil'])
    

    this.empresaService.registrarResultadoPruebaTecnicaCandidato(this.infoProyecto['idProyecto'],this.infoCandidato['idCandidato'],this.infoPerfil['idPerfil'], evaluacion)
    .subscribe((resultado) => {
      this.showSuccess()
      this.routerPath.navigate([`empresa/main`])

    },
        error => {
          if (error.message.toString().includes("404")) {
            this.showError(`No se pudo registrar resultado`)
          } else {
            this.showError(`Ha ocurrido un error: ${error.message}`)
          }
        }
    );

  }

}
