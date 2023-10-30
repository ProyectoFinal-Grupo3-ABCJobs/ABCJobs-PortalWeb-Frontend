import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerProyectosComponent } from './ver-proyectos.component';

describe('VerProyectosComponent', () => {
  let component: VerProyectosComponent;
  let fixture: ComponentFixture<VerProyectosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VerProyectosComponent]
    });
    fixture = TestBed.createComponent(VerProyectosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
