import { Component, EventEmitter, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, BehaviorSubject } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Book } from 'src/app/models/book/book';
import { LineOrder } from 'src/app/models/lineOrder/line-order';
import { Order } from 'src/app/models/order/order';
import { BookService } from 'src/app/services/book/book.service';
import { LineOrderService } from 'src/app/services/lineOrder/line-order.service';
import { OrderServiceService } from 'src/app/services/order/order-service.service';
import { LineOrderListComponent } from '../../lineOrder/lineOrderList/line-order-list/line-order-list.component';
import { AddBookComponent } from '../add-book/add-book.component';

@Component({
  selector: 'app-list-book',
  templateUrl: './list-book.component.html',
  styleUrls: ['./list-book.component.scss']
})
export class ListBookComponent implements OnInit {
 orders : Observable<Order[]> ;
  books: Observable<Book[]>   ;
  order: Order = new Order;
  line :LineOrder = new LineOrder;
  isLinear = false;
  lines: Observable<Array<LineOrder>>   ;
  displayedColumns: string[] = ['Id', 'qte', 'total'];
  
  public event: EventEmitter<any> = new EventEmitter();
 refrechBook = new BehaviorSubject<Boolean>(true) ;
  constructor(public dialog: MatDialog,  private orderService :OrderServiceService ,   private lineService :LineOrderService ,private bookService :BookService ,private route: ActivatedRoute, private router: Router) {
  this.books=this.bookService.getAllBook() ;
  this.lines=this.lineService.getAllLine() ;
  this.orders=this. orderService.getAllOrder() ;
 
  }


  
  ngOnInit(): void {
this.bookService.getAllBook().subscribe(()=>this.books=this.bookService.getAllBook() )
  this.books = this.refrechBook.pipe(switchMap(_=>this.bookService.getAllBook()));
  this.lines = this.lineService.getAllLine();

  this.lines = this.refrechBook.pipe(switchMap(_=>this.lineService.getAllLine()));
  this.orders=this. orderService.getAllOrder() ;
  this.getLastOrder() ;
}

    book:Book = new Book

    public getLastOrder(){
      this.orderService.lastOrdder().subscribe((orderData) => this.order=orderData);
     
    }

  public getbook(id : any ){
    this.bookService.getBookByid(id as number).subscribe((bookData) => this.book=bookData);
   
  }
    public getALL(){
      this.orders=this. orderService.getAllOrder() ;
      this.lines =this.lineService.getAllLine() ;
      this.books = this.bookService.getAllBook() ;
      this.getLastOrder() ;
    }
  
    public deleteLine(id : any ){
  
      this.lineService.deleteLine(id as number).subscribe(() => this.lines= this.lineService.getAllLine());
      
      this.refrechBook.next(true); 
  
    }
  public createLine(){
  this.router.navigate(['/book/get'])}
  onSearchClear() {

    this.applyFilter();
  }
  applyFilter() {

    this.bookService.getAllBook ;
  }

  
  openDialog(id: number): void {
    let dialogRef = this.dialog.open(LineOrderListComponent, {
      width: '600px', data:  { name: id } 
    
    });
    this.bookService.getBookByid(id as number).subscribe((bookData) => this.book=bookData);
    dialogRef.componentInstance.event.subscribe((bookData:any) => this.book=bookData)
  
    this.orders=this. orderService.getAllOrder() ;
  }

  
  pay() {
    this.lines = this.lineService.getAllLine();
   this.lineService.deleteAll().subscribe() ;
   this.orderService.initOrder(this.order).subscribe(data=> this.orders= this.orderService.getAllOrder() )
    this.order.total=0;
   
  }

}


