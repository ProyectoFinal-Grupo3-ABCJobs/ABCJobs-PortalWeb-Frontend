import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerEmparejamientoComponent } from './ver-emparejamiento.component';

describe('VerEmparejamientoComponent', () => {
  let component: VerEmparejamientoComponent;
  let fixture: ComponentFixture<VerEmparejamientoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VerEmparejamientoComponent]
    });
    fixture = TestBed.createComponent(VerEmparejamientoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
