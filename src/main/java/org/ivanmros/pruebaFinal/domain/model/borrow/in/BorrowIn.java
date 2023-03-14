package org.ivanmros.pruebaFinal.domain.model.borrow.in;

import org.ivanmros.pruebaFinal.domain.model.book.BookId;
import org.ivanmros.pruebaFinal.domain.model.user.UserId;

public class BorrowIn {
    private final BookId bookId;

    private final StartDate startDate;

    private final UserId userId;

    public BorrowIn(BookId bookId, StartDate startDate, UserId userId) {
        this.bookId = bookId;
        this.startDate = startDate;
        this.userId = userId;
    }

    public BookId getBookId() {
        return bookId;
    }

    public StartDate getStartDate() {
        return startDate;
    }

    public UserId getUserId() {
        return userId;
    }
}
