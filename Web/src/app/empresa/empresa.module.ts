import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegistroEmpresaComponent } from './registro-empresa/registro-empresa.component'; 
import { CrearProyectoComponent } from './crear-proyecto/crear-proyecto.component';
import { VerProyectosComponent } from './ver-proyectos/ver-proyectos.component';
import { AsignarEmpleadoComponent } from './asignar-empleado/asignar-empleado.component';
import { CrearFichaComponent } from './crear-ficha/crear-ficha.component';


@NgModule({
  declarations: [RegistroEmpresaComponent, CrearProyectoComponent, VerProyectosComponent, AsignarEmpleadoComponent, CrearFichaComponent],
  imports: [
    CommonModule, ReactiveFormsModule, FormsModule
  ],
  exports: [RegistroEmpresaComponent, CrearProyectoComponent,VerProyectosComponent, AsignarEmpleadoComponent, CrearFichaComponent]
})
export class EmpresaModule { }
