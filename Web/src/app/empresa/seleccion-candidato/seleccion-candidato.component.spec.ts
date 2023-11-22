import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeleccionCandidatoComponent } from './seleccion-candidato.component';

describe('SeleccionCandidatoComponent', () => {
  let component: SeleccionCandidatoComponent;
  let fixture: ComponentFixture<SeleccionCandidatoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SeleccionCandidatoComponent]
    });
    fixture = TestBed.createComponent(SeleccionCandidatoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
