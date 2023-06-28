package lk.ijse.dep10.microservices.api;


import lk.ijse.dep10.microservices.entity.Book;
import lk.ijse.dep10.microservices.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookHTTPController {

    private final BookService bookService;

    public BookHTTPController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllCustomers(){
        return bookService.findAllBooks();
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) throws InterruptedException {
        return bookService.saveBook(book);
    }
}
