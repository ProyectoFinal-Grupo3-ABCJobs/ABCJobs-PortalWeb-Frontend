export class Empresa {
    idEmpresa: number;
    razonSocial: string;
    nit: string;
    direccion: String;
    telefono: number;
    idCiudad: number;
  
      constructor(
        idEmpresa: number,
        razonSocial: string,
        nit: string,
        direccion: String,
        telefono: number,
        idCiudad: number
      ) {
          this.idEmpresa = idEmpresa;
          this.razonSocial = razonSocial;
          this.nit = nit;
          this.direccion = direccion;
          this.telefono = telefono;
          this.idCiudad = idCiudad;
      }
  }
