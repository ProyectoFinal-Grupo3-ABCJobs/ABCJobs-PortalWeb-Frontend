import { Proyecto } from "./proyecto";

export class Empresa {
    idEmpresa: number;
    razonSocial: string;
    nit: string;
    direccion: String;
    telefono: number;
    idCiudad: number;
    proyecto: Proyecto;
  
      constructor(
        idEmpresa: number,
        razonSocial: string,
        nit: string,
        direccion: String,
        telefono: number,
        idCiudad: number,
        proyecto: Proyecto
      ) {
          this.idEmpresa = idEmpresa;
          this.razonSocial = razonSocial;
          this.nit = nit;
          this.direccion = direccion;
          this.telefono = telefono;
          this.idCiudad = idCiudad;
          this.proyecto = proyecto;
      }
  }
