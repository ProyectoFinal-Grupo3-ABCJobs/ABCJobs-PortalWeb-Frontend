import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerResultadosEntrevistasComponent } from './ver-resultados-entrevistas.component';

describe('VerResultadosEntrevistasComponent', () => {
  let component: VerResultadosEntrevistasComponent;
  let fixture: ComponentFixture<VerResultadosEntrevistasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VerResultadosEntrevistasComponent]
    });
    fixture = TestBed.createComponent(VerResultadosEntrevistasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
