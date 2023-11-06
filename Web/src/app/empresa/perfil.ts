export class Perfil {
  idPerfil: number;
  idProyecto: number;
  nombre: string;
  descripcion: string;

  constructor(
    idPerfil: number,
    idProyecto: number,
    nombre: string,
    descripcion: string
  ) {
    this.idPerfil = idPerfil;
    this.idProyecto = idProyecto;
    this.nombre = nombre;
    this.descripcion = descripcion;

  }
}
