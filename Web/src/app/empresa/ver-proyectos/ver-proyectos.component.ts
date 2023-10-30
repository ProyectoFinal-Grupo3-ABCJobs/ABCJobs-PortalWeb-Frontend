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
  miObjeto="";
  constructor(private empresaService: EmpresaService) { }

  obtenerProyectos(empresaId:number):void{
    this.empresaService.verProyectos(empresaId)
    .subscribe((proyectos) => {

      this.proyectos.push(proyectos)
      this.objetoJSON = JSON.stringify(this.proyectos[0]);
      this.miObjeto = JSON.parse(this.objetoJSON);
      // console.log("El tipo de dato es ", typeof this.miObjeto)
      // console.log("El tipo contenido es  ", this.miObjeto)
    });
  }
  ngOnInit() {
    
  }

}
