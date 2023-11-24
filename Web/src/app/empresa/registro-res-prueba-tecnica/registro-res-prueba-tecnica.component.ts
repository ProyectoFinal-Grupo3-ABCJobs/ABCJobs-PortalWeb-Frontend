import { Component, OnInit } from '@angular/core';
import { EmpresaService } from '../empresa.service';
import { Router } from '@angular/router';
import { Proyecto } from '../proyecto';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-registro-res-prueba-tecnica',
  templateUrl: './registro-res-prueba-tecnica.component.html',
  styleUrls: ['./registro-res-prueba-tecnica.component.css']
})
export class RegistroResPruebaTecnicaComponent implements OnInit{


  proyectos: Array<Proyecto> = [];
  empresaId: number = 0 ;
  objetoJSON = ""
  datosProyecto="";

  // constructor(private empresaService: EmpresaService,private router: Router) { }
  constructor(
    private empresaService: EmpresaService,
    private routerPath: Router,
    private toastr: ToastrService
  ) { }

  obtenerProyectos(){
    this.empresaService.verProyectos()
    .subscribe((proyectos) => {
      
      this.proyectos.push(proyectos)
      this.objetoJSON = JSON.stringify(this.proyectos[0]);
      this.datosProyecto = JSON.parse(this.objetoJSON);
    });
  }

  listarCandidatosPerfil() {
    this.empresaService.verCadidatosPorPerfil(697)
      .subscribe((resultado) => {
        console.log(resultado)
      },
        error => {
          if (error.message.toString().includes("404")) {
            this.showError(`La empresa no tiene empleados internos asignados`)
          } else {
            this.showError(`Ha ocurrido un error: ${error.message}`)
          }
        })
  }



  ngOnInit() {
    this.obtenerProyectos()
    this.listarCandidatosPerfil()
  }

  cancelCreate() {
    this.routerPath.navigate([`empresa/main`])
  }

  showError(error: string) {
    this.toastr.error(error, "Error")
  }


}
