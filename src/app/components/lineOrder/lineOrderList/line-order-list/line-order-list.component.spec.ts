import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LineOrderListComponent } from './line-order-list.component';

describe('LineOrderListComponent', () => {
  let component: LineOrderListComponent;
  let fixture: ComponentFixture<LineOrderListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LineOrderListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LineOrderListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
