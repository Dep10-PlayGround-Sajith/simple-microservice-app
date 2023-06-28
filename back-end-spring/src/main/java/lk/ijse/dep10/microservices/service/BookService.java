package lk.ijse.dep10.microservices.service;


import lk.ijse.dep10.microservices.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    Book saveBook(Book book) throws InterruptedException;


}
