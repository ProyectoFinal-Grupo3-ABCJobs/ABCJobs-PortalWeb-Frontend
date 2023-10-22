import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistroInfoCandidatoComponent } from './registro-info-candidato/registro-info-candidato.component'; 

@NgModule({
  declarations: [RegistroInfoCandidatoComponent],
  imports: [
    CommonModule, ReactiveFormsModule
  ],
  exports: [RegistroInfoCandidatoComponent]
})
export class EmpresaModule { }
