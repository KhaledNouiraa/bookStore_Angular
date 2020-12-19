import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LineOrder } from 'src/app/models/lineOrder/line-order';

@Injectable({
  providedIn: 'root'
})
export class LineOrderService {
  url  = 'http://localhost:8060/BuildStore/api/line'
  constructor(private http : HttpClient) { 
    
  }
  getAllLine () :Observable<Array<LineOrder>> {
    return this.http.get<Array<LineOrder>>(this.url+`/getAll`); 
  }
  saveLine(lineOrder: LineOrder ,id:number): Observable<LineOrder> {
    return this.http.post(this.url+`/addLine/`+id, lineOrder)
  }
  deleteLine (id:number) :Observable<LineOrder>{
    
    return this.http.delete(this.url+`/deleteLine/`+id)

  }

  deleteAll () :Observable<LineOrder>{
    
    return this.http.delete(this.url+`/deleteAllLine`)

  }

}

