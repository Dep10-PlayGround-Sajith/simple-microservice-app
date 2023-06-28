package lk.ijse.dep10.simplemicroservice.service.impl;

import lk.ijse.dep10.simplemicroservice.dao.BookDAO;
import lk.ijse.dep10.simplemicroservice.entity.Book;
import lk.ijse.dep10.simplemicroservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO;
    @Autowired
    private Validator validator;

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

        String newId = bookDAO.save(book);
        try {
            bookDAO.doSomething();
        } catch (Exception e) {
            System.out.println("Do nothing");
        }
        book.setIsbn(String.valueOf(newId));
        return book;

    }
}
