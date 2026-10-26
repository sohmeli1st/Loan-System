package repository;
import entity.Book;

import entity.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; // Map importu eklendi
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryBookRepository implements BookRepository{
    List<Book> books = new ArrayList<>();
    Map<String, Book> booksMap = new HashMap<>();
    @Override
    public void save(Book b){
        books.add(b);
        booksMap.put(b.getBookId(),b);
    }

    @Override
   public List<Book> findAll(){
        for(Book b : books){
            System.out.println(b);
        }
        return books;
    }

    @Override
   public Optional<Book> findById(String id){
        return Optional.ofNullable(booksMap.get(id));
    }
}
