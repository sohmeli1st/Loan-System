package service;

import entity.User;
import entity.Book;
import entity.Loan;
import exception.BookNotAvailableException;
import exception.LimitExceededException;
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
                .orElseThrow(() -> new BookNotAvailableException("Böyle bir kitap yok"));

        if(!book.isBookAvailable()){
            throw new BookNotAvailableException("Kitap şu an başkasında");
        }

        long userBookCount = activeLoans.stream()
                .filter(loan -> loan.getUser().getUserId().equals(userId))
                .filter(loan -> loan.getReturnDate() == null)
                .count();

        if (userBookCount >= user.getMaxBorrowLimit()) {
            throw new LimitExceededException("Kullanıcı maksimum kitap limitine ulaştı!");
        }

        book.setBookAvailable(false);
        activeLoans.add(new Loan(user, book));
    }
}