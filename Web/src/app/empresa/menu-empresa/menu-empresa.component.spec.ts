import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuEmpresaComponent } from './menu-empresa.component';

describe('MenuEmpresaComponent', () => {
  let component: MenuEmpresaComponent;
  let fixture: ComponentFixture<MenuEmpresaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MenuEmpresaComponent]
    });
    fixture = TestBed.createComponent(MenuEmpresaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
