export class Candidato {
    idCandidato: number;
    tipoIdentificacion: string;
    identificacion: number;
    nombre: String;
    direccion: String;
    telefono: number;
    profesion: String;
    aniosExperiencia: number;
    idCiudad: number;
    idDepartamento: number;
    idPais: number;
    ultimoEstudio: String;
    institucion: String;
    anioGrado: number;
    idCiudadInst: number;
    idDepartamentoInst: number;
    cargoUltimoEmpleo: String;
    empresa: String;
    anioIngreso: number;
    anioRetiro: number;
    estado: Boolean;
    palabrasClave: String;
  
      constructor(
        idCandidato: number,
        tipoIdentificacion: string,
        identificacion: number,
        nombre: String,
        direccion: String,
        telefono: number,
        profesion: String,
        aniosExperiencia: number,
        idCiudad: number,
        idDepartamento: number,
        idPais: number,
        ultimoEstudio: String,
        institucion: String,
        anioGrado: number,
        idCiudadInst: number,
        idDepartamentoInst: number,
        cargoUltimoEmpleo: String,
        empresa: String,
        anioIngreso: number,
        anioRetiro: number,
        estado: Boolean,
        palabrasClave: String
      ) {
          this.idCandidato = idCandidato;
          this.tipoIdentificacion = tipoIdentificacion;
          this.identificacion = identificacion;
          this.nombre = nombre;
          this.direccion = direccion;
          this.telefono = telefono;
          this.profesion = profesion;
          this.aniosExperiencia = aniosExperiencia;
          this.idCiudad = idCiudad;
          this.idDepartamento = idDepartamento;
          this.idPais = idPais;
          this.ultimoEstudio = ultimoEstudio;
          this.institucion = institucion;
          this.anioGrado = anioGrado;
          this.idCiudadInst = idCiudadInst;
          this.idDepartamentoInst = idDepartamentoInst;
          this.cargoUltimoEmpleo = cargoUltimoEmpleo;
          this.empresa = empresa;
          this.anioIngreso = anioIngreso;
          this.anioRetiro = anioRetiro;
          this.estado = estado;
          this.palabrasClave = palabrasClave;
      }
  }