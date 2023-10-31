import { Component,OnInit  } from '@angular/core';
import { Proyecto } from '../proyecto';
import { EmpresaService } from '../empresa.service';

@Component({
  selector: 'app-ver-proyectos',
  templateUrl: './ver-proyectos.component.html',
  styleUrls: ['./ver-proyectos.component.css']
})
export class VerProyectosComponent implements OnInit{

  proyectos: Array<Proyecto> = [];
  empresaId: number = 0 ;
  objetoJSON = ""
  datosProyecto="";
  constructor(private empresaService: EmpresaService) { }

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

}
