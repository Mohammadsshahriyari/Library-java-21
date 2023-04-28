package org.josef.library.services;

import org.josef.library.entities.BorrowBook;

public interface BorrowService {
    BorrowBook issueBook(BorrowBook borrowBook);

    BorrowBook returnBook(BorrowBook borrowBook,float rating);

    BorrowBook findEntry(long borrowed_id, long customer_id);

    void showBorrowRegister();
}
