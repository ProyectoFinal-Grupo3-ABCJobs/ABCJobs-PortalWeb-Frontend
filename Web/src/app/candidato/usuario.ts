export class Usuario {
    id: number;
    usuario: String;
    contrasena: String;
    tipoUsuario: String;
  
      constructor(
        id: number,
        usuario: String,
        contrasena: String,
        tipoUsuario: String,
      ) {
          this.id = id;
          this.usuario = usuario;
          this.contrasena = contrasena;
          this.tipoUsuario = tipoUsuario;

      }
  }