package lk.ijse.dep10.microservices.service.impl;


import lk.ijse.dep10.microservices.dao.BookDAO;
import lk.ijse.dep10.microservices.entity.Book;
import lk.ijse.dep10.microservices.service.BookService;
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
    public List<Book> findAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    @Transactional
    public Book saveBook(Book book) throws InterruptedException {
        Integer newIsbn = bookDAO.save(book);
        try {
            bookDAO.doSomething();
        }catch (Exception e){
            System.out.println("Do nothing");
        }
        book.setIsbn(String.valueOf(newIsbn));
        return book;

    }
}
