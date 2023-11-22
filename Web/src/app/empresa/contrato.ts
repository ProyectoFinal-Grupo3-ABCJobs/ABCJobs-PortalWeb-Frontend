export class Contrato {
    idContrato: number;
    numeroContrato: number;
    idCandidato: number;
    nombreCandidato: string;
    idEmpresa: number;
    idProyecto: number;
    idCargo: number;
  
      constructor(
        idContrato: number,
        numeroContrato: number,
        idCandidato: number,
        nombreCandidato: string,
        idEmpresa: number,
        idProyecto: number,
        idCargo: number
      ) {
          this.idContrato = idContrato;
          this.numeroContrato = numeroContrato;
          this.idCandidato = idCandidato;
          this.nombreCandidato = nombreCandidato;
          this.idEmpresa = idEmpresa;
          this.idProyecto = idProyecto;
          this.idCargo = idCargo;

      }
  }
