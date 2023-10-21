import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistroEmpresaComponent } from './registro-empresa/registro-empresa.component'; 

@NgModule({
  declarations: [RegistroEmpresaComponent],
  imports: [
    CommonModule, ReactiveFormsModule
  ],
  exports: [RegistroEmpresaComponent]
})
export class EmpresaModule { }
