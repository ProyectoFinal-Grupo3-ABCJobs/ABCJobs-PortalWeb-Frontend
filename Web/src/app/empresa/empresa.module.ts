import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistroEmpresaComponent } from './registro-empresa/registro-empresa.component'; 
import { CrearProyectoComponent } from './crear-proyecto/crear-proyecto.component';
import { VerProyectosComponent } from './ver-proyectos/ver-proyectos.component';
import { AsignarEmpleadoComponent } from './asignar-empleado/asignar-empleado.component';
import { NavbarComponent } from '../compartido/navbar/navbar.component';


@NgModule({
  declarations: [RegistroEmpresaComponent, CrearProyectoComponent, VerProyectosComponent, AsignarEmpleadoComponent],
  imports: [
    CommonModule, ReactiveFormsModule
  ],
  exports: [RegistroEmpresaComponent, CrearProyectoComponent,VerProyectosComponent, AsignarEmpleadoComponent]
})
export class EmpresaModule { }
