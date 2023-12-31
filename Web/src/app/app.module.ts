import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BannerComponent } from './compartido/banner/banner.component';
import { NavbarComponent } from './compartido/navbar/navbar.component';
import { FooterComponent } from './compartido/footer/footer.component';
import { HttpClientModule,HTTP_INTERCEPTORS  } from '@angular/common/http';
import { MenuEmpresaComponent } from './empresa/menu-empresa/menu-empresa.component';
import { MenuCandidatoComponent } from './candidato/menu-candidato/menu-candidato.component';
import { MenuFuncionarioComponent } from './funcionario/menu-funcionario/menu-funcionario.component';
import { EmpresaModule } from './empresa/empresa.module'; 
import { CandidatoModule } from './candidato/candidato.module'; 
import { AutenticacionModule } from './autenticacion/autenticacion.module';
import { HttpErrorInterceptorService } from './interceptors/http-error-interceptor.service';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    BannerComponent,
    NavbarComponent,
    FooterComponent,
    MenuEmpresaComponent,
    MenuCandidatoComponent,
    MenuFuncionarioComponent,
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    EmpresaModule,
    CandidatoModule,
    AutenticacionModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 7000,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
    }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
