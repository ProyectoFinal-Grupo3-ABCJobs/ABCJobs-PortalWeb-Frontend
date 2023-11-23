import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from "@auth0/angular-jwt";
import { ToastrService } from 'ngx-toastr';
import { EmpresaService } from '../empresa.service'; 
import { Contrato } from '../contrato';
import { DesempenoEmpleado } from '../desempenoEmpleado';


@Component({
  selector: 'app-registro-desempeno-candidato',
  templateUrl: './registro-desempeno-candidato.component.html',
  styleUrls: ['./registro-desempeno-candidato.component.css']
})
export class RegistrarDesempenoCandidatoComponent implements OnInit {


  helper = new JwtHelperService();
  desempenoForm: FormGroup = new FormGroup({});
  contratos: Array<Contrato> = [];
  mostrarPuntero = false;
  itemSeleccionado: number;
  candidatoSeleccionado: number;
  nombreCandidatoSeleccionado: string;
  botonDesactivado = true;
  formulario: boolean = false;

  constructor(
    private empresaService:EmpresaService,
    private formBuilder: FormBuilder,
    private router: ActivatedRoute,
    private routerPath: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.desempenoForm = this.formBuilder.group({
      calificacion: ["", [Validators.required, Validators.maxLength(100)]],
      aspectosResaltar: ["", [Validators.required, Validators.maxLength(1000),]],
      aspectosMejorar: ["", [Validators.required, Validators.maxLength(1000),]]
    })
    this.verCandidatosContratados()
  }

  verCandidatosContratados(){
    this.empresaService.verCandidatosContratados()
    .subscribe((contrato) => {
      this.contratos = contrato;
      console.log("contrato", contrato)
    });
  }

  registrarDesempenoCandidato(newDesempeno:DesempenoEmpleado) {
    newDesempeno.idContrato = this.candidatoSeleccionado;
    console.log(newDesempeno)
    this.empresaService.registrarDesempenoCandidato(newDesempeno)
      .subscribe(res => {
        this.showSuccess()
        this.routerPath.navigate([`empresa/main`])
        
      },
        error => {
          if (error.statusText === "CONFLICT") {
            this.showError(`Ese empleado ya fue evaluado`)
          } else {
            this.showError(`Ha ocurrido un error: ${error.message}`)
          }

        })
  }

  seleccionCandidato(candidato:any, nombreCandidato:any, ixnumber:number){
    console.log("candidato", candidato)
    this.candidatoSeleccionado = candidato;
    this.nombreCandidatoSeleccionado = nombreCandidato;
    this.itemSeleccionado = ixnumber;
    if (this.candidatoSeleccionado)  {
      this.botonDesactivado = false;
    }else{
      this.botonDesactivado = true;
    }
    console.log(this.botonDesactivado)
  }

  cancelCreate() {
    this.desempenoForm.reset()
    this.routerPath.navigate([`empresa/main`])
  }  

  showError(error: string) {
    this.toastr.error(error, "Error")
  }

  showSuccess() {
    this.toastr.success(`Se ha registrado exitosamente`, "Registro exitoso");
  }

  cambiarEstadoPuntero(estado: boolean): void {
    this.mostrarPuntero = estado;
  }

  mostrarFormulario() {
    this.formulario = true;

  }

}