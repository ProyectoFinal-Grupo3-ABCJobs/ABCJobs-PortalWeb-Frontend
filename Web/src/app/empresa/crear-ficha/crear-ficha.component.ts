import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from "@auth0/angular-jwt";
import { ToastrService } from 'ngx-toastr';
import { EmpresaService } from '../empresa.service';
import { Ficha } from '../ficha';
import { Proyecto } from '../proyecto';
import { EmpleadoInterno } from '../empleadoInterno';
import { Perfil } from '../perfil';

@Component({
  selector: 'app-crear-ficha',
  templateUrl: './crear-ficha.component.html',
  styleUrls: ['./crear-ficha.component.css']
})
export class CrearFichaComponent implements OnInit {

  ficha: Ficha; 
  proyectos: Array<Proyecto> = [];
  empleadosInternos: EmpleadoInterno[] = [];
  perfiles: Perfil[] = [];
  proyectoSeleccionado: Proyecto;
  empleadosSeleccionados: EmpleadoInterno[] = [];
  perfilesSeleccionados: Perfil[] = [];

  constructor(
    private empresaService: EmpresaService,
    private routerPath: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.verProyectos()
  }

  verProyectos() {
    this.empresaService.verProyectos()
      .subscribe((data: any) => {
        this.proyectos = data;
      },
        error => {
          if (error.status === 404) {
            this.showError(error.message)
          } else {
            this.showError(`Ha ocurrido un error: ${error.message}`)
          }
        })
  }

  verEmpleadosInternos() {
    this.empresaService.verEmpleadosInternos()
      .subscribe((data: any) => {
        this.empleadosInternos = data;
      },
        error => {
          if (error.message.toString().includes("404")) {
            this.showError(`La empresa no tiene empleados internos asignados`)
          } else {
            this.showError(`Ha ocurrido un error: ${error.message}`)
          }
        })
  }

  verPerfiles() {
    this.empresaService.verPerfiles()
      .subscribe((data: any) => {
        this.perfiles = data;
      },
        error => {
          if (error.status.toString().includes("404")) {
            this.showError(`El proyecto no tiene perfiles asociados`)
          } else {
            this.showError(`Ha ocurrido un error: ${error.message}`)
          }
        })
  }

  seleccionarProyecto() {
    this.verEmpleadosInternos()
    this.verPerfiles()
  }

  crearFicha() {
    alert("EMPL: " + JSON.stringify(this.empleadosSeleccionados))
    alert("PERFILES: " + JSON.stringify(this.perfilesSeleccionados))
    alert("IDPROYECTO: " + this.proyectoSeleccionado.idProyecto)
    this.ficha = new Ficha(this.proyectoSeleccionado.idProyecto, this.empleadosSeleccionados, this.perfilesSeleccionados);
    
    alert("FICHA: " + JSON.stringify(this.ficha))
    this.empresaService.crearFicha(this.ficha)
      .subscribe(res => {
        this.showSuccess()
        this.routerPath.navigate([`empresa/main`])
      },
        error => {
          this.showError(`Ha ocurrido un error: ${error.message}`)
        })
  }

  cancelCreate() {
    this.routerPath.navigate([`empresa/main`])
  }

  showError(error: string) {
    this.toastr.error(error, "Error")
  }

  showSuccess() {
    this.toastr.success(`Se ha registrado exitosamente`, "Registro exitoso");
  }

}