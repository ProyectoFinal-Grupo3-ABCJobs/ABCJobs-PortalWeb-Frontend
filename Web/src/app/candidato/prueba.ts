export class Prueba {
    idPrueba: number;
    idCandidato: number;
    idEmpresa: number;
    tipoPrueba: String;
    fechaPrueba: Date;
    resultado: String;
    observacion: String;
    estado: String;

  
      constructor(
        idPrueba: number,
        idCandidato: number,
        idEmpresa: number,
        tipoPrueba: String,
        fechaPrueba: Date,
        resultado: String,
        observacion: String,
        estado: String

      ) {
          this.idPrueba = idPrueba;
          this.idCandidato = idCandidato;
          this.idEmpresa = idEmpresa;
          this.tipoPrueba = tipoPrueba;
          this.fechaPrueba = fechaPrueba;
          this.resultado = resultado;
          this.observacion = observacion;
          this.estado = estado;


      }
  }