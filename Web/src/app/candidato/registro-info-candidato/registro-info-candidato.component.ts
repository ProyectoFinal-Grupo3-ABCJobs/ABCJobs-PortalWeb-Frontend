import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtHelperService } from "@auth0/angular-jwt";
import { ToastrService } from 'ngx-toastr';
import { CandidatoService } from '../candidato.service'; 
import { Candidato } from '../candidato';

@Component({
  selector: 'app-registro-info-candidato',
  templateUrl: './registro-info-candidato.component.html',
  styleUrls: ['./registro-info-candidato.component.css']
})
export class RegistroInfoCandidatoComponent {

  helper = new JwtHelperService();
  candidatoForm: FormGroup = new FormGroup({});

  constructor(
    private candidatoService:CandidatoService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.candidatoForm = this.formBuilder.group({
      tipoIdentificacion: ["", [Validators.required]],
      identificacion: ["", [Validators.required, Validators.maxLength(20)]],
      nombre: ["", [Validators.required, Validators.maxLength(150)]],
      direccion: ["", [Validators.required, Validators.maxLength(150)]],
      telefono: ["", [Validators.required]],
      profesion: ["", [Validators.required, Validators.maxLength(150)]],
      aniosExperiencia: ["", [Validators.required]],
      idPais: ["", [Validators.required]],
      idDepartamento: ["", [Validators.required]],
      idCiudad: ["", [Validators.required]],
      ultimoEstudio: ["", [Validators.required, Validators.maxLength(150)]],
      institucion: ["", [Validators.required, Validators.maxLength(150)]],
      anioGrado: ["", [Validators.required]],
      idDepartamentoInst: ["", [Validators.required]],
      idCiudadInst: ["", [Validators.required]],
      cargoUltimoEmpleo: ["", [Validators.required, Validators.maxLength(150)]],
      empresa: ["", [Validators.required, Validators.maxLength(150)]],
      anioIngreso: ["", [Validators.required]],
      anioRetiro: [""],
      palabrasClave: ["", [Validators.required, Validators.maxLength(300)]],
    })
  }

  registrarCandidato(newCandidato:Candidato) {
    this.candidatoService.registrarInfoCandidato(newCandidato)
      .subscribe(res => {
        this.router.navigate([``])
        this.showSuccess()
      },
        error => {
          if (error.statusText === "CONFLICT") {
            this.showError(`Ya se encuentra registrado un candidato con la identificación ingresada`)
          } else {
            this.showError(`Ha ocurrido un error: ${error.message}`)
          }

        })
  }

  showError(error: string) {
    this.toastr.error(error, "Error")
  }

  showSuccess() {
    this.toastr.success(`Se ha registrado exitosamente`, "Registro exitoso");
  }



}
