export class Contrato {
    numeroContrato: number;
    idCandidato: number;
    idEmpresa: number;
    idProyecto: number;
    idCargo: number;
  
      constructor(
        numeroContrato: number,
        idCandidato: number,
        idEmpresa: number,
        idProyecto: number,
        idCargo: number
      ) {
          this.numeroContrato = numeroContrato;
          this.idCandidato = idCandidato;
          this.idEmpresa = idEmpresa;
          this.idProyecto = idProyecto;
          this.idCargo = idCargo;

      }
  }
