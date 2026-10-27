package service;

import entity.User;
import entity.Book;
import entity.Loan;
import exception.BookNotAvailableException;
import exception.UserNotFoundException;
import repository.BookRepository;
import repository.UserRepository;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    List<Loan> activeLoans = new ArrayList<>();
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LibraryService(UserRepository userRepository, BookRepository bookRepository){
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public void borrowBook(String userId, String bookId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Böyle bir kullanıcı yok"));

        Book book = bookRepository.findById(bookId)
                // Daha önce BookNotAvailableException yaratmıştık, onu kullanıyoruz:
                .orElseThrow(() -> new BookNotAvailableException("Böyle bir kitap yok"));

        if(!book.isBookAvailable()){
            throw new BookNotAvailableException("Kitap şu an başkasında");
        }

        // LİMİT KONTROLÜ VE KAYDETME İŞLEMLERİ BURAYA GELECEK
    }
}