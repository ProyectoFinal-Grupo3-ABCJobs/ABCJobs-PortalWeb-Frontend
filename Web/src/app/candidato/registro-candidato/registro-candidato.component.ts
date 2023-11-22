import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { JwtHelperService } from "@auth0/angular-jwt";
import { ToastrService } from 'ngx-toastr';
import { CandidatoService } from '../candidato.service'; 
import { Usuario } from '../usuario';

@Component({
  selector: 'app-registro-candidato',
  templateUrl: './registro-candidato.component.html',
  styleUrls: ['./registro-candidato.component.css']
})
export class RegistroCandidatoComponent implements OnInit {

  helper = new JwtHelperService();
  usuarioForm: FormGroup = new FormGroup({});
  contrasenaCoincide: boolean = false;

  constructor(
    private candidatoService:CandidatoService,
    private formBuilder: FormBuilder,
    private router: ActivatedRoute,
    private routerPath: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.usuarioForm = this.formBuilder.group({
      usuario: ["", [Validators.required, Validators.maxLength(50)]],
      contrasena: ["", [Validators.required, Validators.maxLength(90), Validators.minLength(5)]],
      confirmarContrasena: ["", [Validators.required, Validators.maxLength(90)]],
      tipoUsuario: [""],
      
    })
  }

  registrarUsuario(tipoUsuario: string) {
    const newUsuario: Usuario = this.usuarioForm.value;
    if (tipoUsuario === 'CANDIDATO') {
      newUsuario.tipoUsuario = "CANDIDATO"
      console.log(newUsuario)
      this.candidatoService.registrarUsuario(newUsuario)
        .subscribe(res => {
          this.routerPath.navigate([`/candidato/registroInformacion`])
          this.showSuccess()
        },
          error => {
            if (error.statusText === "CONFLICT") {
              this.showError(`Ya se encuentra registrado ese usuario`)
            } else {
              this.showError(`Ha ocurrido un error: ${error.message}`)
            }
  
          })
    } else if (tipoUsuario === 'EMPRESA') {
      newUsuario.tipoUsuario = "EMPRESA"
      this.candidatoService.registrarUsuario(newUsuario)
      .subscribe(
          (res: any) => {
            const userId = res.id;
          this.routerPath.navigate([`/empresa/registroInformacion`, userId]);
          this.showSuccess();
        },
          error => {
            if (error.statusText === "CONFLICT") {
              this.showError(`Ya se encuentra registrado ese usuario`)
            } else {
              this.showError(`Ha ocurrido un error: ${error.message}`)
            }
  
          })
    }
  }

  validarContrasenas() {
    const contrasena = this.usuarioForm.get('contrasena')?.value;
    const confirmarContrasena = this.usuarioForm.get('confirmarContrasena')?.value;

    if (contrasena !='' && contrasena !== confirmarContrasena) {
      this.contrasenaCoincide = true;
    } else {
      this.contrasenaCoincide = false;
    }
    console.log(this.contrasenaCoincide)
    console.log(contrasena)
    console.log(confirmarContrasena)
  }


  cancelCreate() {
    this.usuarioForm.reset()
    this.routerPath.navigate([`login`])
  } 

  showError(error: string) {
    this.toastr.error(error, "Error")
  }

  showSuccess() {
    this.toastr.success(`Se ha registrado exitosamente`, "Registro exitoso");
  }

}
