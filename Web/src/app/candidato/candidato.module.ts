import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistroCandidatoComponent } from './registro-candidato/registro-candidato.component'; 
import { RegistroInfoCandidatoComponent } from './registro-info-candidato/registro-info-candidato.component'; 

@NgModule({
  declarations: [RegistroInfoCandidatoComponent, RegistroCandidatoComponent],
  imports: [
    CommonModule, ReactiveFormsModule
  ],
  exports: [RegistroInfoCandidatoComponent, RegistroCandidatoComponent]
})
export class CandidatoModule { }
