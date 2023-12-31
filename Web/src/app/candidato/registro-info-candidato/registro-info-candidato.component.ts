import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { JwtHelperService } from "@auth0/angular-jwt";
import { ToastrService } from 'ngx-toastr';
import { CandidatoService } from '../candidato.service'; 
import { Candidato } from '../candidato';

@Component({
  selector: 'app-registro-info-candidato',
  templateUrl: './registro-info-candidato.component.html',
  styleUrls: ['./registro-info-candidato.component.css']
})
export class RegistroInfoCandidatoComponent implements OnInit {

  helper = new JwtHelperService();
  candidatoForm: FormGroup = new FormGroup({});

  mostrarParte2: boolean = false;
  palabrasClaveList: string[] = [];
  palabrasClaveValid: boolean = false;
  userId: string;

  constructor(
    private candidatoService:CandidatoService,
    private formBuilder: FormBuilder,
    private router: ActivatedRoute,
    private routerPath: Router,
    private toastr: ToastrService
  ) { }


  ngOnInit() {
    this.userId = this.router.snapshot.paramMap.get('userId');
    console.log(this.userId);
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
      palabrasClave: [""],
      idUsuario: this.userId ,
    })
  }


  registrarCandidato(newCandidato:Candidato) {
    newCandidato.palabrasClave = this.palabrasClaveList.join(', ');
    console.log(newCandidato)
    this.candidatoService.registrarInfoCandidato(newCandidato)
      .subscribe(res => {
        this.routerPath.navigate([`login`])
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

    // Función para agregar una palabra clave
    agregarPalabraClave() {
      const palabra = this.candidatoForm.get('palabrasClave')?.value;
      if (palabra) {
        // Capitalizar la palabra antes de agregarla a la lista
        const palabraCapitalizada = palabra.charAt(0).toUpperCase() + palabra.slice(1);
        this.palabrasClaveList.push(palabraCapitalizada);
        this.candidatoForm.get('palabrasClave')?.setValue('');
        console.log(this.palabrasClaveList);
      }
      if (this.palabrasClaveList.length > 0) {
        this.palabrasClaveValid = true;
      } else {
        this.palabrasClaveValid = false;
      }
      console.log(this.palabrasClaveValid);
    }
  
    // Función para quitar una palabra clave
    quitarPalabraClave(index: number) {
      this.palabrasClaveList.splice(index, 1);
      console.log(this.palabrasClaveList);
      if (this.palabrasClaveList.length > 0) {
        this.palabrasClaveValid = true;
      } else {
        this.palabrasClaveValid = false;
      }
    }

  mostrarSegundaParte() {
    console.log("segunda parte")
    this.mostrarParte2 = true;

  }
  
  cancelCreate() {
    this.candidatoForm.reset()
    this.routerPath.navigate([`login`])
  } 

  showError(error: string) {
    this.toastr.error(error, "Error")
  }

  showSuccess() {
    this.toastr.success(`Se ha registrado exitosamente`, "Registro exitoso");
  }



}
