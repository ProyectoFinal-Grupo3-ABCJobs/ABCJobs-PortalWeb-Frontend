import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from "@auth0/angular-jwt";
import { ToastrService } from 'ngx-toastr';
import { EmpresaService } from '../empresa.service'; 
import { Empresa } from '../empresa';

@Component({
  selector: 'app-registro-empresa',
  templateUrl: './registro-empresa.component.html',
  styleUrls: ['./registro-empresa.component.css']
})
export class RegistroEmpresaComponent implements OnInit {


  helper = new JwtHelperService();
  empresaForm: FormGroup = new FormGroup({});

  constructor(
    private empresaService:EmpresaService,
    private formBuilder: FormBuilder,
    private router: ActivatedRoute,
    private routerPath: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.empresaForm = this.formBuilder.group({
      razonSocial: ["", [Validators.required, Validators.maxLength(100), Validators.minLength(4)]],
      nit: ["", [Validators.required, Validators.maxLength(20)]],
      direccion: ["", [Validators.required, Validators.maxLength(100)]],
      telefono: ["", [Validators.required]],
      idPais: ["", [Validators.required]],
      idDepartamento: ["", [Validators.required]],
      idCiudad: ["", [Validators.required]],
    })
  }

  registrarEmpresa(newEmpresa:Empresa) {
    this.empresaService.crearEmpresa(newEmpresa)
      .subscribe(res => {
        this.routerPath.navigate([`candidato/registro`])
        this.showSuccess()
      },
        error => {
          if (error.statusText === "CONFLICT") {
            this.showError(`El nit o razón social ya se encuentra registrada`)
          } else {
            this.showError(`Ha ocurrido un error: ${error.message}`)
          }

        })
  }

  cancelCreate() {
    this.empresaForm.reset()
    this.routerPath.navigate([`candidato/registro`])
  }  

  showError(error: string) {
    this.toastr.error(error, "Error")
  }

  showSuccess() {
    this.toastr.success(`Se ha registrado exitosamente`, "Registro exitoso");
  }

}