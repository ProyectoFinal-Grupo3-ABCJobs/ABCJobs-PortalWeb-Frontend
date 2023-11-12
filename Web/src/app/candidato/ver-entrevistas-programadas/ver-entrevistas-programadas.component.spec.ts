import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerEntrevistasProgramadasComponent } from './ver-entrevistas-programadas.component';

describe('VerEntrevistasProgramadasComponent', () => {
  let component: VerEntrevistasProgramadasComponent;
  let fixture: ComponentFixture<VerEntrevistasProgramadasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VerEntrevistasProgramadasComponent]
    });
    fixture = TestBed.createComponent(VerEntrevistasProgramadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
