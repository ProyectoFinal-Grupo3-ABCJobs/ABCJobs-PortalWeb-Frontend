import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegistroEmpresaComponent } from './registro-empresa/registro-empresa.component'; 
import { CrearProyectoComponent } from './crear-proyecto/crear-proyecto.component';
import { VerProyectosComponent } from './ver-proyectos/ver-proyectos.component';
import { AsignarEmpleadoComponent } from './asignar-empleado/asignar-empleado.component';
import { CrearFichaComponent } from './crear-ficha/crear-ficha.component';
import { VerEmparejamientoComponent } from './ver-emparejamiento/ver-emparejamiento.component';
import { SeleccionCandidatoComponent } from './seleccion-candidato/seleccion-candidato.component';
import { RegistrarDesempenoCandidatoComponent } from './registro-desempeno-candidato/registro-desempeno-candidato.component';


@NgModule({
  declarations: [RegistroEmpresaComponent, CrearProyectoComponent, VerProyectosComponent, AsignarEmpleadoComponent, CrearFichaComponent, VerEmparejamientoComponent, SeleccionCandidatoComponent, RegistrarDesempenoCandidatoComponent],
  imports: [
    CommonModule, ReactiveFormsModule, FormsModule
  ],
  exports: [RegistroEmpresaComponent, CrearProyectoComponent,VerProyectosComponent, AsignarEmpleadoComponent, CrearFichaComponent, RegistrarDesempenoCandidatoComponent]
})
export class EmpresaModule { }
