export class Prueba {
    idPrueba: number;
    idCandidato: number;
    idEmpresa: number;
    tipoPrueba: String;
    fechaPrueba: Date;
    resultado: String;
    observaciones: String;
    estado: String;
    empresaNombre: String;
    idProyecto: number;

  
      constructor(
        idPrueba: number,
        idCandidato: number,
        idEmpresa: number,
        tipoPrueba: String,
        fechaPrueba: Date,
        resultado: String,
        observaciones: String,
        estado: String,
        empresaNombre: String,
        idProyecto: number,

      ) {
          this.idPrueba = idPrueba;
          this.idCandidato = idCandidato;
          this.idEmpresa = idEmpresa;
          this.tipoPrueba = tipoPrueba;
          this.fechaPrueba = fechaPrueba;
          this.resultado = resultado;
          this.observaciones = observaciones;
          this.estado = estado;
          this.empresaNombre = empresaNombre;
          this.idProyecto = idProyecto;


      }
  }