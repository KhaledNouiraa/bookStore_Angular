import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../../models/book/book';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class BookService {
  
 url  = 'http://localhost:8060/BuildStore/api/book'
  constructor(private http : HttpClient) { 
    
  }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  getAllBook () :Observable<Book[]> {
    return this.http.get<Book[]>(this.url+`/getAll`); 
  }
  updateBook( book:Book): Observable<any> {
    return this.http.put(this.url+`/updateBook`, book)
  }
  saveBook(book: Book): Observable<Book> {
    return this.http.post(this.url+`/addBook`, book)
  }
 
  getBookByid(idBook :number) :Observable<Book> {
    return this.http.get(this.url+`/getBook/`+idBook)}
  deleteBook (id:number) :Observable<Book>{
    
    return this.http.delete(this.url+`/deleteBook/`+id)

  }

}
