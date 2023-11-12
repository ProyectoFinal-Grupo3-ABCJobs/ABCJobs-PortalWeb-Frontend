export class Entrevista {
    idEntrevista: number;
    idCandidato: number;
    idEmpresa: number;
    idProyecto: number;
    fechaEntrevista: Date;
    cargoAplica: String;
    resultado: String;
    estado: String;

  
      constructor(
        idEntrevista: number,
        idCandidato: number,
        idEmpresa: number,
        idProyecto: number,
        fechaEntrevista: Date,
        cargoAplica: String,
        resultado: String,
        estado: String

      ) {
          this.idEntrevista = idEntrevista;
          this.idCandidato = idCandidato;
          this.idEmpresa = idEmpresa;
          this.idProyecto = idProyecto;
          this.fechaEntrevista = fechaEntrevista;
          this.cargoAplica = cargoAplica;
          this.resultado = resultado;
          this.estado = estado;


      }
  }