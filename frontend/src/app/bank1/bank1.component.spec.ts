import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Bank1Component } from './bank1.component';

describe('Bank1Component', () => {
  let component: Bank1Component;
  let fixture: ComponentFixture<Bank1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Bank1Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Bank1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
