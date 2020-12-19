import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Book } from 'src/app/models/book/book';
import { BookService } from 'src/app/services/book/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.scss']
})
export class AddBookComponent implements OnInit {
  book:Book = new Book
  books : Observable<Array<Book>>
  message :string =''
  action :string ='Add success !'
  public event: EventEmitter<any> = new EventEmitter();
  constructor(  public dialogRef: MatDialogRef<AddBookComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private bookService : BookService, private router: Router ,private _snackBar: MatSnackBar) {
      this.books=this.bookService.getAllBook() ;
    }

  ngOnInit(): void {
    
  }

  
  addBook() {
    this.bookService.saveBook(this.book).subscribe(data=> this.books= this.bookService.getAllBook())
    this.event.emit({data: this.book});
    this.dialogRef.close();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  openSnackBar() {
    this._snackBar.open(this.message, this.action, {
      duration: 2000,
    });
  }
}
