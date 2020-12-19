import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/order/order';
import { OrderServiceService } from 'src/app/services/order/order-service.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {
order:Order = new Order ;
  constructor(private orderService : OrderServiceService) { }

  ngOnInit(): void {
  }
createOrder() {
  this.order.total=0 ;
  this.orderService.saveOrder(this.order).subscribe() ;


}
}
