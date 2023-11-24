import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroResPruebaTecnicaComponent } from './registro-res-prueba-tecnica.component';

describe('RegistroResPruebaTecnicaComponent', () => {
  let component: RegistroResPruebaTecnicaComponent;
  let fixture: ComponentFixture<RegistroResPruebaTecnicaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegistroResPruebaTecnicaComponent]
    });
    fixture = TestBed.createComponent(RegistroResPruebaTecnicaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
