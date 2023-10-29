import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistroEmpresaComponent } from './registro-empresa/registro-empresa.component'; 
import { CrearProyectoComponent } from './crear-proyecto/crear-proyecto.component';

@NgModule({
  declarations: [RegistroEmpresaComponent, CrearProyectoComponent],
  imports: [
    CommonModule, ReactiveFormsModule
  ],
  exports: [RegistroEmpresaComponent, CrearProyectoComponent]
})
export class EmpresaModule { }
