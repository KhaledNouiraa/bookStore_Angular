import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from 'src/app/models/order/order';

@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {
  url  = 'http://localhost:8060/BuildStore/api/order'
  constructor(private http : HttpClient) { 
    
  }
  getAllOrder () :Observable<Array<Order>> {
    return this.http.get<Array<Order>>(this.url+`/getAll`); 
  }
  saveOrder(lineOrder: Order ): Observable<Order> {
    return this.http.post(this.url+`/addOrder`, lineOrder)
  }
  deleteOrder (id:number) :Observable<Order>{
    
    return this.http.delete(this.url+`/deleteOrder/`+id)

  }
  lastOrdder() : Observable<Order>{
    return this.http.get<Order>(this.url+`/lastOrder`); 
  }
  initOrder(lineOrder: Order ): Observable<Order> {
    return this.http.put(this.url+`/InitOrder`, lineOrder)
  }
}
