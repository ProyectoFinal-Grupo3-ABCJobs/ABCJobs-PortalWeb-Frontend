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
  userId: string;

  constructor(
    private empresaService:EmpresaService,
    private formBuilder: FormBuilder,
    private router: ActivatedRoute,
    private routerPath: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.userId = this.router.snapshot.paramMap.get('userId');
    console.log(this.userId);
    this.empresaForm = this.formBuilder.group({
      razonSocial: ["", [Validators.required, Validators.maxLength(100), Validators.minLength(4)]],
      nit: ["", [Validators.required, Validators.maxLength(20)]],
      direccion: ["", [Validators.required, Validators.maxLength(100)]],
      telefono: ["", [Validators.required]],
      idPais: ["", [Validators.required]],
      idDepartamento: ["", [Validators.required]],
      idCiudad: ["", [Validators.required]],
      idUsuario: this.userId ,
    })
  }

  registrarEmpresa(newEmpresa:Empresa) {
    console.log("registrarEmpresa", newEmpresa)

    this.empresaService.crearEmpresa(newEmpresa)
      .subscribe(res => {
        this.showSuccess()
        this.empresaForm.reset()
        this.routerPath.navigate([`empresa/main`])
        
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
    this.routerPath.navigate([`login`])
  }  

  showError(error: string) {
    this.toastr.error(error, "Error")
  }

  showSuccess() {
    this.toastr.success(`Se ha registrado exitosamente`, "Registro exitoso");
  }

}