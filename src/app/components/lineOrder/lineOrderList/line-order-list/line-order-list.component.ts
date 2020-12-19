import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { LineOrder } from 'src/app/models/lineOrder/line-order';
import { LineOrderService } from 'src/app/services/lineOrder/line-order.service';
import { switchMap } from 'rxjs/operators';
import { Book } from 'src/app/models/book/book';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { BookService } from 'src/app/services/book/book.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AddBookComponent } from 'src/app/components/book/add-book/add-book.component';
@Component({
  selector: 'app-line-order-list',
  templateUrl: './line-order-list.component.html',
  styleUrls: ['./line-order-list.component.scss']
})
export class LineOrderListComponent implements OnInit {
  line:LineOrder = new LineOrder
  
  id: number = 0;
  book: Book = new Book;
  form: FormGroup;
  lines: Observable<Array<LineOrder>>   ;
  displayedColumns: string[] = ['Id', 'title', 'author','quantity'];
  public event: EventEmitter<any> = new EventEmitter();
  constructor(public dialogRef: MatDialogRef<AddBookComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {name: number},
    public bookService: BookService,
    private route: ActivatedRoute,
    private router: Router ,
    private lineService :LineOrderService 
  ) {   this.form = new FormGroup({
    title: new FormControl('', [Validators.required]),
    body: new FormControl('', Validators.required)
  });
  this.lines=this.lineService.getAllLine() ;

    }
  ngOnInit(): void {
    
     ;
    this.bookService.getBookByid(this.data.name).subscribe((data: Book)=>
      this.book = data
    
    );
    
    this.form = new FormGroup({
      id: new FormControl('', [Validators.required]),
      title: new FormControl('', [Validators.required]),
      author: new FormControl('', Validators.required) ,
      price_unit: new FormControl('', [Validators.required])
    });

    this.lines = this.lineService.getAllLine();
    this.lineService.getAllLine().subscribe(data => {
    console.log(data);})
    
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
  get f(){
    return this.form.controls;
  }
     
  submit(){
    console.log(this.form.value);
   
  }
   
 
  addline() {
    
    this.lineService.saveLine(this.line,this.data.name).subscribe((line) => this.lines= this.lineService.getAllLine());
    this.event.emit({data: this.book});
    this.dialogRef.close();
  }
  
  onSearchClear() {

    this.applyFilter();
  }
  applyFilter() {

    this.lineService.getAllLine ;
  }
}


