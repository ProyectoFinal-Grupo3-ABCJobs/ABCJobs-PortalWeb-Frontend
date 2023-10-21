import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MenuEmpresaComponent } from './empresa/menu-empresa/menu-empresa.component';
import { MenuCandidatoComponent } from './candidato/menu-candidato/menu-candidato.component';
import { MenuFuncionarioComponent } from './funcionario/menu-funcionario/menu-funcionario.component';
import { RegistroCandidatoComponent } from './candidato/registro-candidato/registro-candidato.component';
import { RegistroInfoCandidatoComponent } from './candidato/registro-info-candidato/registro-info-candidato.component';

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
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
