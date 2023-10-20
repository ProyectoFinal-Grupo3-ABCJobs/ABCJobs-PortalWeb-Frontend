import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroCandidatoComponent } from './registro-candidato.component';

describe('RegistroCandidatoComponent', () => {
  let component: RegistroCandidatoComponent;
  let fixture: ComponentFixture<RegistroCandidatoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegistroCandidatoComponent]
    });
    fixture = TestBed.createComponent(RegistroCandidatoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
