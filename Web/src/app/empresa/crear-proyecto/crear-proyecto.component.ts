import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from "@auth0/angular-jwt";
import { ToastrService } from 'ngx-toastr';
import { EmpresaService } from '../empresa.service'; 
import { Proyecto } from '../proyecto';

@Component({
  selector: 'app-crear-proyecto',
  templateUrl: './crear-proyecto.component.html',
  styleUrls: ['./crear-proyecto.component.css']
})
export class CrearProyectoComponent implements OnInit {


  helper = new JwtHelperService();
  proyectoForm: FormGroup = new FormGroup({});

  constructor(
    private empresaService:EmpresaService,
    private formBuilder: FormBuilder,
    private router: ActivatedRoute,
    private routerPath: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.proyectoForm = this.formBuilder.group({
      nombreProyecto: ["", [Validators.required, Validators.maxLength(100), Validators.minLength(4)]],
      fechaInicio: ["", [Validators.required]],
      numeroColaboradores: [""]
    })
  }

  crearProyecto(newProyecto:Proyecto) {
    this.empresaService.crearProyecto(newProyecto)
      .subscribe(res => {
        this.showSuccess()
        this.proyectoForm.reset()
        this.routerPath.navigate([`empresa/main`])
        
      },
        error => {
          if (error.statusText === "CONFLICT") {
            this.showError(`Ya existe un proyecto con ese nombre`)
          } else {
            this.showError(`Ha ocurrido un error: ${error.message}`)
          }

        })
  }

  cancelCreate() {
    this.proyectoForm.reset()
    this.routerPath.navigate([`empresa/main`])
  }  

  showError(error: string) {
    this.toastr.error(error, "Error")
  }

  showSuccess() {
    this.toastr.success(`Se ha registrado exitosamente`, "Registro exitoso");
  }

}