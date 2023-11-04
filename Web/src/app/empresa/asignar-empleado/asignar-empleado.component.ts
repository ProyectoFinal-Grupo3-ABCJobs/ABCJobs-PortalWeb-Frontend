import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from "@auth0/angular-jwt";
import { ToastrService } from 'ngx-toastr';
import { EmpresaService } from '../empresa.service';
import { EmpleadoInterno } from '../empleadoInterno'; 

@Component({
  selector: 'app-asignar-empleado',
  templateUrl: './asignar-empleado.component.html',
  styleUrls: ['./asignar-empleado.component.css']
})
export class AsignarEmpleadoComponent implements OnInit {


  helper = new JwtHelperService();
  empleadoForm: FormGroup = new FormGroup({});

  constructor(
    private empresaService:EmpresaService,
    private formBuilder: FormBuilder,
    private router: ActivatedRoute,
    private routerPath: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.empleadoForm = this.formBuilder.group({
      tipoIdentificacion: ["", [Validators.required]],
      identificacion: ["", [Validators.required, Validators.maxLength(20)]],
      nombre: ["", [Validators.required, Validators.maxLength(100), Validators.minLength(4)]],
      cargo: ["", [Validators.required, Validators.maxLength(100), Validators.minLength(4)]]
    })
  }

  asignarEmpleado(newEmpleado:EmpleadoInterno) {
    this.empresaService.asignarEmpleado(newEmpleado)
      .subscribe(res => {
        this.showSuccess()
        this.empleadoForm.reset()
        this.routerPath.navigate([`empresa/main`])
        
      },
        error => {
          if (error.statusText === "CONFLICT") {
            this.showError(`Ya existe un empleado con ese documento`)
          } else {
            this.showError(`Ha ocurrido un error: ${error.message}`)
          }

        })
  }

  cancelCreate() {
    this.empleadoForm.reset()
    this.routerPath.navigate([`empresa/main`])
  }  

  showError(error: string) {
    this.toastr.error(error, "Error")
  }

  showSuccess() {
    this.toastr.success(`Se ha registrado exitosamente`, "Registro exitoso");
  }

}