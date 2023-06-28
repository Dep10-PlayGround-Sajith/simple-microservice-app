package lk.ijse.dep10.microservices.dao.impl;


import lk.ijse.dep10.microservices.dao.BookDAO;
import lk.ijse.dep10.microservices.entity.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Book> BOOK_ROW_MAPPER = (rs, rowNum) -> Book.builder()
            .isbn(rs.getString("isbn"))
            .title(rs.getString("title")).build();

    public BookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", BOOK_ROW_MAPPER);
    }

    @Override
    public Integer save(Book book) {
        jdbcTemplate.update(con -> {
            PreparedStatement stm = con.prepareStatement
                    ("INSERT INTO book (isbn, title) VALUES (?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, book.getIsbn());
            stm.setString(2, book.getTitle());
            return stm;
        });
    return null;
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void doSomething() {
        jdbcTemplate.update("INSERT INTO book (isbn, title) VALUES (?,?)",
                "978-3-16-148410-0", "Programming Fundamentals");
        throw new RuntimeException("Failed to insert");
    }
}
