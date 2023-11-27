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
  botonDesactivado = true;  
  botonEmparejarDesactivado=true;
  mostrarPuntero = false;
  ficha: Ficha; 
  proyectos: Array<Proyecto> = [];
  empleadosInternos: EmpleadoInterno[] = [];
  perfiles: Perfil[] = [];
  proyectoSeleccionado: Proyecto;
  empleadosSeleccionados: EmpleadoInterno[] = [];
  perfilesSeleccionados: Perfil[] = [];
  itemSeleccionado: number;
  itemSeleccionadoEmpleados:number[] = []
  itemSeleccionadoPerfil:number[] = []
  seleccionados: number[] = [];
  listaEmpleadosInternos: EmpleadoInterno[] = [];
  listaPerfiles: Perfil[] = [];
  ejecutarEmparejamiento: boolean = false;
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

    this.itemSeleccionado = ixnumber;
    this.proyectoSeleccionado = proyecto;
    if (this.proyectoSeleccionado && this.listaEmpleadosInternos.length>0 && this.listaPerfiles.length>0)  {
      this.botonDesactivado = false;
    }else{
      this.botonDesactivado = true;
    }
  }
  
  seleccionEmpleadosInternos(empleadoInterno:any,ixnumber:number){

    const index = this.itemSeleccionadoEmpleados.indexOf(ixnumber);
    //this.itemSeleccionadoEmpleados = ixnumber;
     
    if (index === -1) {
      this.itemSeleccionadoEmpleados.push(ixnumber);
      this.listaEmpleadosInternos.push(empleadoInterno)
    } else {
      this.itemSeleccionadoEmpleados.splice(ixnumber, 1);
      this.listaEmpleadosInternos.splice(empleadoInterno,1)
    }
    // console.log("El array del empleado interno es: ",this.itemSeleccionadoEmpleados) 
    // console.log("Los empleados internos seleccionados son: ",this.listaEmpleadosInternos)
    if (this.proyectoSeleccionado && this.listaEmpleadosInternos.length>0 && this.listaPerfiles.length>0)  {
      this.botonDesactivado = false;
    }else{
      this.botonDesactivado = true;
    }
  }

  seleccionPerfiles(perfil:any,ixnumber:number){


    const index = this.itemSeleccionadoPerfil.indexOf(ixnumber);
    //this.itemSeleccionadoEmpleados = ixnumber;
      
    if (index === -1) {
      this.itemSeleccionadoPerfil.push(ixnumber);
      this.listaPerfiles.push(perfil)
    } else {
      this.itemSeleccionadoPerfil.splice(ixnumber, 1);
      this.listaPerfiles.splice(perfil,1)
    }
    // console.log("Los perfiles seleccionados son: ",this.listaPerfiles)
    if (this.proyectoSeleccionado && this.listaEmpleadosInternos.length>0 && this.listaPerfiles.length>0)  {
      this.botonDesactivado = false;
    }else{
      this.botonDesactivado = true;
    }
  }

  crearFicha() {
    if (this.proyectoSeleccionado && this.listaEmpleadosInternos.length>0 && this.listaPerfiles.length>0)  {      
     
      this.empleadosSeleccionados = this.listaEmpleadosInternos;
      this.perfilesSeleccionados = this.listaPerfiles;
      
      this.ficha = new Ficha(
        this.proyectoSeleccionado.idProyecto,
        this.empleadosSeleccionados,
        this.perfilesSeleccionados
      );

      this.empresaService.crearFicha(this.ficha).subscribe(
        (res) => {
          this.botonEmparejarDesactivado=false;
          this.botonDesactivado = true;
          this.showSuccessCrearFicha();
          // this.routerPath.navigate([`empresa/main`]);
          
        },
        (error) => {
          this.showError(`Ha ocurrido un error: ${error.message}`);
        }
      );

    
    
    }
    
    else{
      console.log("Falta seleccionar")
    }
  }

  iniciarEmparejamiento(){
    this.empresaService.ejecutarMotorEmparejamiento().subscribe(
      (result) => {
        this.showSuccessEmparejamiento();
        this.routerPath.navigate([`empresa/main`]);
      },
      (error) => {
        this.showError(`Ha ocurrido un error al emparejar: ${error.message}`);
      }
    );

  }

  cancelCreate() {
    this.routerPath.navigate([`empresa/main`])
  }

  showError(error: string) {
    this.toastr.error(error, "Error")
  }


 

  showSuccessCrearFicha() {
    
    this.toastr.success(`Se ha registrado exitosamente`, "Registro exitoso");
  }

  showSuccess() {

    this.toastr.success(`Se ha registrado exitosamente`, "Registro exitoso");
  }
  
  showSuccessEmparejamiento() {
    
    this.toastr.success(`Emparejamiento ejecutado`, "Registro exitoso");
  }


  
  cambiarEstadoPuntero(estado: boolean): void {
    this.mostrarPuntero = estado;
  }

}