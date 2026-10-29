package org.example;

import entity.Book;
import entity.User;
import repository.InMemoryBookRepository;
import repository.InMemoryUserRepository;
import repository.BookRepository;
import repository.UserRepository;
import service.LibraryService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new InMemoryBookRepository();
        UserRepository userRepository = new InMemoryUserRepository();
        LibraryService libraryService = new LibraryService(userRepository, bookRepository);

        bookRepository.save(new Book("Clean Code"));
        bookRepository.save(new Book("Java Concurrency in Practice"));
        bookRepository.save(new Book("Effective Java"));
        bookRepository.save(new Book("Design Patterns"));
        bookRepository.save(new Book("Algoritmalar"));

        User user1 = new User("Muharrem");
        userRepository.save(user1);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("=== KUTUPHANE YONETIM SISTEMINE HOS GELDINIZ ===");
        System.out.println("Aktif Kullanici: " + user1.getUserName() + " (ID: " + user1.getUserId() + ")");

        while (!exit) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Tum Kitaplari Listele");
            System.out.println("2. Kitap Adiyla Ara");
            System.out.println("3. Kitap Kirala (Borrow)");
            System.out.println("4. Cikis");
            System.out.print("Seciminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- KUTUPHANEDEKİ KİTAPLAR ---");
                    List<Book> allBooks = bookRepository.findAll();
                    for (Book b : allBooks) {
                        String durum = b.isBookAvailable() ? "Müsait" : "Kirada";
                        System.out.println("- " + b.getBookName() + " | Durum: " + durum + " | ID: " + b.getBookId());
                    }
                    break;

                case 2:
                    System.out.print("Aramak istediginiz kelime/harf: ");
                    String query = scanner.nextLine().toLowerCase();

                    List<Book> filteredBooks = bookRepository.findAll().stream()
                            .filter(b -> b.getBookName().toLowerCase().contains(query))
                            .collect(Collectors.toList());

                    System.out.println("\n--- ARAMA SONUÇLARI ---");
                    if (filteredBooks.isEmpty()) {
                        System.out.println("Aradiginiz kritere uygun kitap bulunamadi.");
                    } else {
                        for (Book b : filteredBooks) {
                            String durum = b.isBookAvailable() ? "Müsait" : "Kirada";
                            System.out.println("- " + b.getBookName() + " | Durum: " + durum + " | ID: " + b.getBookId());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Kiralamak istediginiz kitabin ID'sini girin: ");
                    String bookId = scanner.nextLine();

                    try {
                        libraryService.borrowBook(user1.getUserId(), bookId);
                        System.out.println("İslem Basarili: Kitap kiralandi!");
                    } catch (Exception e) {
                        System.out.println("Hata: " + e.getMessage());
                    }
                    break;

                case 4:
                    exit = true;
                    System.out.println("Sistemden cikiliyor...");
                    break;

                default:
                    System.out.println("Gecersiz secim, tekrar deneyin.");
            }
        }
        scanner.close();
    }
}