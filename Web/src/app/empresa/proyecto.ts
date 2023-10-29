export class Proyecto {
    idEmpresa: number;
    nombreProyecto: string;
    fechaInicio: Date;
    numeroColaboradores: string;
  
      constructor(
        idEmpresa: number,
        nombreProyecto: string,
        fechaInicio: Date,
        numeroColaboradores: string
      ) {
          this.idEmpresa = idEmpresa;
          this.nombreProyecto = nombreProyecto;
          this.fechaInicio = fechaInicio;
          this.numeroColaboradores = numeroColaboradores;

      }
  }
