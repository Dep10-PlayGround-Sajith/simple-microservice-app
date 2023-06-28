package lk.ijse.dep10.simplemicroservice.service.impl;

import lk.ijse.dep10.simplemicroservice.dao.BookDAO;
import lk.ijse.dep10.simplemicroservice.entity.Book;
import lk.ijse.dep10.simplemicroservice.service.BookService;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Transactional
    @Override
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    @Transactional
    @Override
    public Book saveBook(Book book) throws InterruptedException {
        Integer newId = bookDAO.save(book);
        try {
            bookDAO.doSomething();
        }catch (Exception e){
            System.out.println("Do nothing");
        }
        book.setIsbn(String.valueOf(newId));
        return book;



    }
}
