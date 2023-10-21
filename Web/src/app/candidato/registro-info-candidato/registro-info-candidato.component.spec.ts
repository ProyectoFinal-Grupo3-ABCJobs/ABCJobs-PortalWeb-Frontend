import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroInfoCandidatoComponent } from './registro-info-candidato.component';

describe('RegistroInfoCandidatoComponent', () => {
  let component: RegistroInfoCandidatoComponent;
  let fixture: ComponentFixture<RegistroInfoCandidatoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegistroInfoCandidatoComponent]
    });
    fixture = TestBed.createComponent(RegistroInfoCandidatoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
