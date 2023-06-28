package lk.ijse.dep10.simplemicroservice.service;

import lk.ijse.dep10.simplemicroservice.entity.Book;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


public interface BookService {
    List<Book> getAllBooks();
    Book saveBook(Book book) throws InterruptedException;


}
