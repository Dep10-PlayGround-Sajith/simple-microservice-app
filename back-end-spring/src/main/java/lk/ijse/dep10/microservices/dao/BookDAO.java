package lk.ijse.dep10.microservices.dao;


import io.swagger.models.auth.In;
import lk.ijse.dep10.microservices.entity.Book;

import java.util.List;

public interface BookDAO {

    List<Book> findAll();

    Integer save(Book book);

    void doSomething();
}
