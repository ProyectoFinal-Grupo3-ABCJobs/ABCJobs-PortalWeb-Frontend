export class Entrevista {
    idEntrevista: number;
    idCandidato: number;
    candidatoNombre: String;
    idEmpresa: number;
    empresaNombre: String;
    idProyecto: number;
    proyectoNombre: String;
    fechaEntrevista: String;
    idPerfil: number;
    perfilDescripcion: String;
    detalles: String;
    estado: String;
    aprobado: String;

  
      constructor(
        idEntrevista: number,
        idCandidato: number,
        candidatoNombre: String,
        idEmpresa: number,
        empresaNombre: String,
        idProyecto: number,
        proyectoNombre: String,
        fechaEntrevista: String,
        idPerfil: number,
        perfilDescripcion: String,
        detalles: String,
        estado: String,
        aprobado: String,

      ) {
          this.idEntrevista = idEntrevista;
          this.idCandidato = idCandidato;
          this.candidatoNombre = candidatoNombre;
          this.idEmpresa = idEmpresa;
          this.empresaNombre = empresaNombre;
          this.idProyecto = idProyecto;
          this.proyectoNombre = proyectoNombre;
          this.fechaEntrevista = fechaEntrevista;
          this.idPerfil = idPerfil;
          this.perfilDescripcion = perfilDescripcion;
          this.detalles = detalles;
          this.estado = estado;
          this.aprobado = aprobado;


      }
  }