import { Component, EventEmitter, OnInit } from '@angular/core';
import { ActivatedRoute, Router ,NavigationEnd} from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { Book } from 'src/app/models/book/book';
import { switchMap } from 'rxjs/operators';
import { BookService } from 'src/app/services/book/book.service';
import { MatDialog } from '@angular/material/dialog';
import { AddBookComponent } from '../add-book/add-book.component';
import { EditBookComponent } from '../edit-book/edit-book.component';


@Component({
  selector: 'app-get-book',
  templateUrl: './get-book.component.html',
  styleUrls: ['./get-book.component.scss']
})

export class GetBookComponent implements OnInit {
  books: Observable<Array<Book>>   ;

  displayedColumns: string[] = ['Id', 'title', 'author', 'date','price','quantity','actions'];
  
  public event: EventEmitter<any> = new EventEmitter();
 refrechBook = new BehaviorSubject<Boolean>(true) ;
  constructor(public dialog: MatDialog,  private bookService :BookService ,private route: ActivatedRoute, private router: Router) {
  this.books=this.bookService.getAllBook() ;
  }


  
  ngOnInit(): void {

  this.books = this.refrechBook.pipe(switchMap(_=>this.bookService.getAllBook()));
  this.refrechBook.next(true); 
  }

    book:Book = new Book
  addBook() {
    this.bookService.saveBook(this.book).subscribe((book) => this.books= this.bookService.getAllBook());
     this.refrechBook.next(true); 
  }
  updateBook(){
    this.bookService.updateBook(this.book).subscribe((book) => this.books= this.bookService.getAllBook());
     this.refrechBook.next(true); 

  }
  public deletePost(id : any ){
  
    this.bookService.deleteBook(id as number).subscribe(data=> this.books= this.bookService.getAllBook()
    )
    
   
    window.location.reload(); 
  }
  public getbook(id : any ){
    this.bookService.getBookByid(id as number).subscribe((bookData) => this.book=bookData);
   
  }
    
  
  
  public createLine(){
  this.router.navigate(['/book/get'])}
  onSearchClear() {

    this.applyFilter();
  }
  applyFilter() {

    this.bookService.getAllBook ;
  }

  
  openDialog(): void {
    let dialogRef = this.dialog.open(AddBookComponent, {
      width: '600px',
    
    });
    dialogRef.componentInstance.event.subscribe( () => this.books= this.bookService.getAllBook())
    this.books = this.refrechBook.pipe(switchMap(_=>this.bookService.getAllBook()));
    this.ngOnInit()
    
  }
  openDialogEdit(id: number): void {
    let dialogRef = this.dialog.open(EditBookComponent, {
      width: '600px',data:  { name: id } 
    
    });
    dialogRef.componentInstance.event.subscribe( () => this.books= this.bookService.getAllBook())
    this.books = this.refrechBook.pipe(switchMap(_=>this.bookService.getAllBook()));
    this.ngOnInit()
    
  }
  

}


