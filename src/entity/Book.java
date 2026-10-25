package entity;

import java.util.*;

public class Book {
    private String bookName;
    private boolean isBookAvailable = true;
    private String bookId;

    //CONS
    public Book(String bookName){
        setBookName(bookName);
        bookId = UUID.randomUUID().toString();
    }

    //GETTERS / SETTERS
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public boolean isBookAvailable() {
        return isBookAvailable;
    }

    public void setBookAvailable(boolean bookAvailable) {
        isBookAvailable = bookAvailable;
    }

    //HASHCODE
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookId);
    }
}
