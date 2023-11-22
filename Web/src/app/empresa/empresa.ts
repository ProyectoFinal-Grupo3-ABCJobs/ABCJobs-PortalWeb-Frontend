import { Proyecto } from "./proyecto";

export class Empresa {
    idEmpresa: number;
    razonSocial: string;
    nit: string;
    direccion: String;
    telefono: number;
    idCiudad: number;
    proyecto: Proyecto;
    idUsuario: number;
  
      constructor(
        idEmpresa: number,
        razonSocial: string,
        nit: string,
        direccion: String,
        telefono: number,
        idCiudad: number,
        proyecto: Proyecto,
        idUsuario: number,

      ) {
          this.idEmpresa = idEmpresa;
          this.razonSocial = razonSocial;
          this.nit = nit;
          this.direccion = direccion;
          this.telefono = telefono;
          this.idCiudad = idCiudad;
          this.proyecto = proyecto;
          this.idUsuario = idUsuario;
      }
  }
