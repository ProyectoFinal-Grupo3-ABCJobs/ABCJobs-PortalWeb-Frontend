import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistroCandidatoComponent } from './registro-candidato/registro-candidato.component'; 
import { RegistroInfoCandidatoComponent } from './registro-info-candidato/registro-info-candidato.component'; 
import { VerEntrevistasProgramadasComponent } from './ver-entrevistas-programadas/ver-entrevistas-programadas.component';
import { VerResultadosEntrevistasComponent } from './ver-resultados-entrevistas/ver-resultados-entrevistas.component';
import { VerResultadoPruebasComponent } from './ver-resultado-pruebas/ver-resultado-pruebas.component';

@NgModule({
  declarations: [RegistroInfoCandidatoComponent, RegistroCandidatoComponent, VerEntrevistasProgramadasComponent, VerResultadosEntrevistasComponent, VerResultadoPruebasComponent],
  imports: [
    CommonModule, ReactiveFormsModule
  ],
  exports: [RegistroInfoCandidatoComponent, RegistroCandidatoComponent, VerEntrevistasProgramadasComponent, VerResultadosEntrevistasComponent, VerResultadoPruebasComponent]
})
export class CandidatoModule { }
