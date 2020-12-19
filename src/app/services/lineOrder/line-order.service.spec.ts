import { TestBed } from '@angular/core/testing';

import { LineOrderService } from './line-order.service';

describe('LineOrderService', () => {
  let service: LineOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LineOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
