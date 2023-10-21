import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BannerComponent } from './banner/banner.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { FooterComponent } from './footer/footer.component';
import { HttpClientModule } from '@angular/common/http';
import { MenuEmpresaComponent } from './empresa/menu-empresa/menu-empresa.component';
import { MenuCandidatoComponent } from './candidato/menu-candidato/menu-candidato.component';
import { MenuFuncionarioComponent } from './funcionario/menu-funcionario/menu-funcionario.component';
import { RegistroCandidatoComponent } from './candidato/registro-candidato/registro-candidato.component';
import { RegistroInfoCandidatoComponent } from './candidato/registro-info-candidato/registro-info-candidato.component';
import { EmpresaModule } from './empresa/empresa.module'; 

@NgModule({
  declarations: [
    AppComponent,
    BannerComponent,
    NavbarComponent,
    LoginComponent,
    FooterComponent,
    MenuEmpresaComponent,
    MenuCandidatoComponent,
    MenuFuncionarioComponent,
    RegistroCandidatoComponent,
    RegistroInfoCandidatoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    EmpresaModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 7000,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
