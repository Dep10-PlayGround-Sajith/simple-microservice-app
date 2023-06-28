import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Book} from "./dto/book";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  bookList:Array<Book>=[];
    constructor(private http:HttpClient) {
      this.getBooks(http);
}

saveBook(txtIsbn:HTMLInputElement,txtTitle:HTMLInputElement):void{
  if(!(txtIsbn.value.trim()&& txtTitle.value.trim())){
    txtIsbn.select();
    txtTitle.select();
    return;
    }
  this.http.post<Book>('http://localhost:8080/app/api/v1/books',
    new Book(txtIsbn.value,txtTitle.value)).subscribe(book =>{
      this.bookList.push(book);
      txtIsbn.value='';
      txtIsbn.focus();
    });
  }
  updateBook(txtIsbn:HTMLInputElement,txtTitle:HTMLInputElement):void{
    if(!(txtIsbn.value.trim()&& txtTitle.value.trim())){
      txtIsbn.select();
      txtTitle.select();
      return;
    }
    this.http.patch<Book>('http://localhost:8080/app/api/v1/books',
      new Book(txtIsbn.value,txtTitle.value)).subscribe(book =>{
      this.bookList.push(book);
      txtIsbn.value='';
      txtIsbn.focus();
    });
    this.getBooks(this.http);
  }
  getBooks(http:HttpClient):void{
    http.get<Array<Book>>('http://localhost:8080/app/api/v1/books')
      .subscribe(bookList=>this.bookList=bookList);
  }
}
