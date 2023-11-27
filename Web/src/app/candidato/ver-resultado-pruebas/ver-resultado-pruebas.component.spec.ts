/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { VerResultadoPruebasComponent } from './ver-resultado-pruebas.component';

describe('VerResultadoPruebasComponent', () => {
  let component: VerResultadoPruebasComponent;
  let fixture: ComponentFixture<VerResultadoPruebasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerResultadoPruebasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerResultadoPruebasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
