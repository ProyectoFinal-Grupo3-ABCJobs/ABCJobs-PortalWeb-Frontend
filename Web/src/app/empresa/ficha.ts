import { EmpleadoInterno } from "./empleadoInterno";
import { Perfil } from "./perfil";

export class Ficha {
  idFicha: number;
  idProyecto: number;
  empleados: EmpleadoInterno[];
  perfiles: Perfil[];

  constructor(
    idProyecto: number,
    empleados: EmpleadoInterno[],
    perfiles: Perfil[]
  ) {
    this.idProyecto = idProyecto;
    this.empleados = empleados;
    this.perfiles = perfiles;

  }
}
