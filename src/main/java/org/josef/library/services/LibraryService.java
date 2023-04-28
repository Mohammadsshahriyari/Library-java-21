package org.josef.library.services;

import org.josef.library.entities.Book;

public interface LibraryService {
    Book addBook(Book book);

    boolean removeBook(Book book);

    Book updateBook(Book book);

    Book findById(long id);

    Book findByTitle(String title);

    Book findByAuthor(String author);

    Book findByGeneral(String general);

    Book makeBookAvailable(long id, boolean available);

    Book makeBookUnavailable(long id, boolean available);

    void displayAllBooks();

}
