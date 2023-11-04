export class EmpleadoInterno {
    idEmpleado: number;
    tipoIdentificacion: string;
    identificacion: string;
    nombre: string;
    cargo: string;
    idEmpresa: number;
  
      constructor(
        idEmpleado: number,
        tipoIdentificacion: string,
        identificacion: string,
        nombre: string,
        cargo: string,
        idEmpresa: number,
      ) {
        this.idEmpleado = idEmpleado;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.cargo = cargo;
        this.idEmpresa = idEmpresa;

      }
  }
