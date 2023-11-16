import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './autenticacion/login/login.component';
import { MenuEmpresaComponent } from './empresa/menu-empresa/menu-empresa.component';
import { MenuCandidatoComponent } from './candidato/menu-candidato/menu-candidato.component';
import { MenuFuncionarioComponent } from './funcionario/menu-funcionario/menu-funcionario.component';
import { RegistroCandidatoComponent } from './candidato/registro-candidato/registro-candidato.component';
import { RegistroInfoCandidatoComponent } from './candidato/registro-info-candidato/registro-info-candidato.component';
import { RegistroEmpresaComponent } from './empresa/registro-empresa/registro-empresa.component'; 
import { VerProyectosComponent } from './empresa/ver-proyectos/ver-proyectos.component';
import { CrearProyectoComponent } from './empresa/crear-proyecto/crear-proyecto.component';
import { AsignarEmpleadoComponent } from './empresa/asignar-empleado/asignar-empleado.component';
import { CrearFichaComponent } from './empresa/crear-ficha/crear-ficha.component';
import { VerEmparejamientoComponent } from './empresa/ver-emparejamiento/ver-emparejamiento.component';
import { SeleccionCandidatoComponent } from './empresa/seleccion-candidato/seleccion-candidato.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
    // pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    // pathMatch: 'full'
  },
  {
    // path: 'registro/:userId/:userToken',
    path: 'empresa/main',
    component: MenuEmpresaComponent
  },
  {
    // path: 'registro/:userId/:userToken',
    path: 'candidato/main',
    component: MenuCandidatoComponent
  },
  {
    // path: 'registro/:userId/:userToken',
    path: 'funcionario/main',
    component: MenuFuncionarioComponent
  },
  {
    path: 'candidato/registro',
    component: RegistroCandidatoComponent
  },
  {
    path: 'candidato/registroInformacion',
    component: RegistroInfoCandidatoComponent
  },
  {
    path: 'empresa/registroInformacion',
    component: RegistroEmpresaComponent
  },
  {
    path: 'empresa/verProyectos',
    component: VerProyectosComponent
  },
  {
    path: 'empresa/crearProyecto',
    component: CrearProyectoComponent
  },
  {
    path: 'empresa/asignarEmpleado',
    component: AsignarEmpleadoComponent
  },
  {
    path: 'empresa/crearFicha',
    component: CrearFichaComponent
  },
  {
    path: 'empresa/verEmparejamiento',
    component: VerEmparejamientoComponent
  }
  ,
  {
    path: 'empresa/seleccionCandidatos',
    component: SeleccionCandidatoComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
