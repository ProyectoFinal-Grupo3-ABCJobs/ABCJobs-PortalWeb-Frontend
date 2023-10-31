import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistroEmpresaComponent } from './registro-empresa/registro-empresa.component'; 
import { CrearProyectoComponent } from './crear-proyecto/crear-proyecto.component';
import { VerProyectosComponent } from './ver-proyectos/ver-proyectos.component';
import { NavbarComponent } from '../compartido/navbar/navbar.component';


@NgModule({
  declarations: [RegistroEmpresaComponent, CrearProyectoComponent, VerProyectosComponent],
  imports: [
    CommonModule, ReactiveFormsModule
  ],
  exports: [RegistroEmpresaComponent, CrearProyectoComponent,VerProyectosComponent]
})
export class EmpresaModule { }
