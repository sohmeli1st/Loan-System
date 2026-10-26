package repository;

import entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    void save(Book book);
    List<Book> findAll();
    Optional<Book> findById(String id);


}
