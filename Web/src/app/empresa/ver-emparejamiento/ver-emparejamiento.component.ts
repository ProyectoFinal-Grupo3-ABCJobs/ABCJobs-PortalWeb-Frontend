import { Component, OnInit } from '@angular/core';
import { EmpresaService } from '../empresa.service';
import { Proyecto } from '../proyecto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ver-emparejamiento',
  templateUrl: './ver-emparejamiento.component.html',
  styleUrls: ['./ver-emparejamiento.component.css']
})
export class VerEmparejamientoComponent implements OnInit{

  proyectos: Array<Proyecto> = [];
  empresaId: number = 0 ;
  objetoJSON = ""
  datosProyecto="";
  constructor(private empresaService: EmpresaService,private router: Router) { }

  obtenerProyectos(){
    this.empresaService.verProyectos()
    .subscribe((proyectos) => {

      this.proyectos.push(proyectos)
      this.objetoJSON = JSON.stringify(this.proyectos[0]);
      this.datosProyecto = JSON.parse(this.objetoJSON);
    });
  }
  ngOnInit() {
    this.obtenerProyectos()
  }

  irAMain(){
    this.router.navigate(['/empresa/main']);
  }



}
