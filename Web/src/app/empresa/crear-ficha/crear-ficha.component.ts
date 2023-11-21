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
  mostrarPuntero = false;
  ficha: Ficha; 
  proyectos: Array<Proyecto> = [];
  empleadosInternos: EmpleadoInterno[] = [];
  perfiles: Perfil[] = [];
  proyectoSeleccionado: Proyecto;
  empleadosSeleccionados: EmpleadoInterno[] = [];
  perfilesSeleccionados: Perfil[] = [];
  itemSeleccionado: any;
  itemSeleccionadoEmpleados:number[] = []
  itemSeleccionadoPerfil:number[] = []
  seleccionados: number[] = [];
  constructor(
    private empresaService: EmpresaService,
    private routerPath: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.verProyectos()
    this.verEmpleadosInternos()
    this.verPerfiles()
  }

  verProyectos() {
    this.empresaService.verProyectosSinFicha()
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
    this.empresaService.verTodosPerfiles()
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


  seleccionProyecto(proyecto:any,ixnumber:number){
    // const index = this.seleccionados.indexOf(ixnumber);
    this.itemSeleccionado = ixnumber;
    console.log("El item seleccionado es: ",this.itemSeleccionado)  
    // if (index === -1) {
    //   this.seleccionados.push(ixnumber);
    // } else {
    //   this.seleccionados.splice(ixnumber, 1);
    // }
  }
  
  seleccionEmpleadosInternos(proyecto:any,ixnumber:number){
    const index = this.itemSeleccionadoEmpleados.indexOf(ixnumber);
    //this.itemSeleccionadoEmpleados = ixnumber;
    console.log("El item seleccionado es: ",this.itemSeleccionadoEmpleados)  
    if (index === -1) {
      this.itemSeleccionadoEmpleados.push(ixnumber);
    } else {
      this.itemSeleccionadoEmpleados.splice(ixnumber, 1);
    }
  }

  seleccionPerfiles(proyecto:any,ixnumber:number){
    const index = this.itemSeleccionadoPerfil.indexOf(ixnumber);
    //this.itemSeleccionadoEmpleados = ixnumber;
    console.log("El item seleccionado es: ",this.itemSeleccionadoPerfil)  
    if (index === -1) {
      this.itemSeleccionadoPerfil.push(ixnumber);
    } else {
      this.itemSeleccionadoPerfil.splice(ixnumber, 1);
    }
  }

  crearFicha() {
    this.ficha = new Ficha(this.proyectoSeleccionado.idProyecto, this.empleadosSeleccionados, this.perfilesSeleccionados);
    
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

  cambiarEstadoPuntero(estado: boolean): void {
    this.mostrarPuntero = estado;
  }

}