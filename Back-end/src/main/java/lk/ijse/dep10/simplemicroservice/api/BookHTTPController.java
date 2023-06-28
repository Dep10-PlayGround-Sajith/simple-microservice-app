package lk.ijse.dep10.simplemicroservice.api;

import lk.ijse.dep10.simplemicroservice.entity.Book;
import lk.ijse.dep10.simplemicroservice.service.BookService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookHTTPController {

    private final BookService bookService;


    public BookHTTPController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book saveBook(@RequestBody @Validated Book book) throws InterruptedException {
        return bookService.saveBook(book);
    }
}