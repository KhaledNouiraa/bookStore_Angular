import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models/book/book';
import { BookService } from 'src/app/services/book/book.service';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { observableToBeFn } from 'rxjs/internal/testing/TestScheduler';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.scss']
})
export class EditBookComponent implements OnInit {
  books : Observable<Array<Book>>
  id: number = 0;
  book: Book = new Book;
  form: FormGroup;
  public event: EventEmitter<any> = new EventEmitter();
  constructor(
    public bookService: BookService,
    private route: ActivatedRoute,
    private router: Router ,
    public dialogRef: MatDialogRef<EditBookComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {name: number},
  ) { this.books=this.bookService.getAllBook() ;   
    this.form = new FormGroup({
    title: new FormControl('', [Validators.required]),
    body: new FormControl('', Validators.required)
  });}
  ngOnInit(): void {
    
    this.bookService.getBookByid(this.data.name).subscribe((data: Book)=>{
      this.book = data;
    });
    
    this.form = new FormGroup({
      id: new FormControl('', [Validators.required]),
      title: new FormControl('', [Validators.required]),
      author: new FormControl('', Validators.required),
      price_unit: new FormControl('', Validators.required),
      date: new FormControl('', Validators.required),
      quantity: new FormControl('', Validators.required)
    });
  }
   
  get f(){
    return this.form.controls;
  }
     
  submit(){
    console.log(this.form.value);
    this.bookService.updateBook( this.form.value).subscribe(data=> this.books= this.bookService.getAllBook()
    )
    this.event.emit({data: this.book});
    this.dialogRef.close();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
   
}
