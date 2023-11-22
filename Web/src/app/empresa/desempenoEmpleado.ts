export class DesempenoEmpleado {
    idDesempeno: number;
    idContrato: number;
    calificacion: string;
    aspectosResaltar: string;
    aspectosMejorar: string;
  
      constructor(
        idDesempeno: number,
        idContrato: number,
        calificacion: string,
        aspectosResaltar: string,
        aspectosMejorar: string

      ) {
            this.idDesempeno = idDesempeno;
            this.idContrato = idContrato;
            this.calificacion = calificacion;
            this.aspectosResaltar = aspectosResaltar;
            this.aspectosMejorar = aspectosMejorar;

      }
  }