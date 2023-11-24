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
import { VerEntrevistasProgramadasComponent } from './candidato/ver-entrevistas-programadas/ver-entrevistas-programadas.component';
import { VerResultadosEntrevistasComponent } from './candidato/ver-resultados-entrevistas/ver-resultados-entrevistas.component';
import { VerResultadoPruebasComponent } from './candidato/ver-resultado-pruebas/ver-resultado-pruebas.component';
import { RegistrarDesempenoCandidatoComponent } from './empresa/registro-desempeno-candidato/registro-desempeno-candidato.component';
import { RegistroResPruebaTecnicaComponent } from './empresa/registro-res-prueba-tecnica/registro-res-prueba-tecnica.component';

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
    path: 'empresa/registroInformacion/:userId',
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
  },
  {
    path: 'candidato/entrevistasProgramadas',
    component: VerEntrevistasProgramadasComponent
  },
  {
    path: 'candidato/resultadosEntrevistas',
    component: VerResultadosEntrevistasComponent
  },
  {
    path: 'candidato/resultadosPruebas',
    component: VerResultadoPruebasComponent
  },
  {
    path: 'empresa/registroDesempeno',
    component: RegistrarDesempenoCandidatoComponent
  },
  {
    path: 'empresa/registroResultadoPruebaTecnicaCand',
    component: RegistroResPruebaTecnicaComponent
  }


  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
