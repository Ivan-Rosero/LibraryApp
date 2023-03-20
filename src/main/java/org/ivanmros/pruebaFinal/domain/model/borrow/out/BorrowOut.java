package org.ivanmros.pruebaFinal.domain.model.borrow.out;

import org.ivanmros.pruebaFinal.domain.model.book.*;
import org.ivanmros.pruebaFinal.domain.model.book.BookName;
import org.ivanmros.pruebaFinal.domain.model.book.BookStatus;
import org.ivanmros.pruebaFinal.domain.model.borrow.in.StartDate;
import org.ivanmros.pruebaFinal.domain.model.user.UserId;
import org.ivanmros.pruebaFinal.domain.model.user.UserName;

public class BorrowOut {

    private final BorrowId borrowId;

    private final UserId userId;

    private final UserName userName;

    private final BookId bookId;

    private final BookName bookName;

    private BookStatus bookStatus;

    private final StartDate startDate;
    private final EndDate endDate;

    private ReturnDate returnDate;
    private BorrowStatus borrowStatus;

    private PenaltyFeeBoolean penaltyFeeBoolean;

    public BorrowOut(BorrowId borrowId, UserId userId, UserName userName, BookId bookId, BookName bookName,
                     BookStatus bookStatus, StartDate startDate, EndDate endDate, ReturnDate returnDate,
                     BorrowStatus borrowStatus, PenaltyFeeBoolean penaltyFeeBoolean) {
        this.borrowId = borrowId;
        this.userId = userId;
        this.userName = userName;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookStatus = bookStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.returnDate = returnDate;
        this.borrowStatus = borrowStatus;
        this.penaltyFeeBoolean = penaltyFeeBoolean;
    }

    public BorrowId getBorrowId() {
        return borrowId;
    }

    public UserId getUserId() {
        return userId;
    }

    public UserName getUserName() {
        return userName;
    }

    public BookId getBookId() {
        return bookId;
    }

    public BookName getBookName() {
        return bookName;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public StartDate getStartDate() {
        return startDate;
    }

    public EndDate getEndDate() {
        return endDate;
    }

    public ReturnDate getReturnDate() {
        return returnDate;
    }

    public BorrowStatus getBorrowStatus() {
        return borrowStatus;
    }

    public PenaltyFeeBoolean getPenaltyFeeBoolean() {
        return penaltyFeeBoolean;
    }
}
