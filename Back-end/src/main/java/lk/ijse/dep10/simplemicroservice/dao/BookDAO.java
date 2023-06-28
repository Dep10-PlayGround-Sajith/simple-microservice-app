package lk.ijse.dep10.simplemicroservice.dao;

import lk.ijse.dep10.simplemicroservice.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();
    Integer save(Book book);

    void doSomething();

}
