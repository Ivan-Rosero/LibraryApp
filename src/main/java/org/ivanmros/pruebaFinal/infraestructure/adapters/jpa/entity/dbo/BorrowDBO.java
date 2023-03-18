package org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo;

import jakarta.persistence.*;
import lombok.*;
import org.ivanmros.pruebaFinal.domain.model.book.BookId;
import org.ivanmros.pruebaFinal.domain.model.book.BookName;
import org.ivanmros.pruebaFinal.domain.model.book.BookStatus;
import org.ivanmros.pruebaFinal.domain.model.borrow.in.StartDate;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.*;
import org.ivanmros.pruebaFinal.domain.model.user.UserId;
import org.ivanmros.pruebaFinal.domain.model.user.UserName;

import java.time.LocalDate;

@Entity
@Table(name="borrow")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BORROW")
    private Integer borrowId;
    @Column(name = "ID_USER")
    private String userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "ID_BOOK")
    private Integer bookId;
    @Column(name = "BOOK_NAME")
    private String bookName;
    @Column(name = "BOOK_STATUS")
    private Boolean bookStatus;
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "END_DATE")
    private LocalDate endDate;
    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;
    @Column(name = "BORROW_STATUS")
    private Boolean borrowStatus;
    @Column(name = "FEE_BOOLEAN")
    private Boolean penaltyFeeBoolean;

    //Primer version
//    public static BorrowOut toDomain(BorrowDBO borrowDBO){
//        return new BorrowOut(
//                new BorrowId(borrowDBO.getBorrowId()),
//                new UserId(borrowDBO.getUserId()),
//                new UserName(borrowDBO.getUserName()),
//                new BookId(borrowDBO.getBookId()),
//                new BookName(borrowDBO.getBookName()),
//                new BookStatus(borrowDBO.getBookStatus()),
//                new StartDate(borrowDBO.getStartDate()),
//                new EndDate(borrowDBO.getEndDate()),
//                new ReturnDate(borrowDBO.getReturnDate()),
//                new BorrowStatus(borrowDBO.getBorrowStatus()),
//                new PenaltyFeeBoolean(borrowDBO.getPenaltyFeeBoolean())
//        );
//    }

    public BorrowOut toDomain(){
        return new BorrowOut(
                new BorrowId(this.getBorrowId()),
                new UserId(this.getUserId()),
                new UserName(this.getUserName()),
                new BookId(this.getBookId()),
                new BookName(this.getBookName()),
                new BookStatus(this.getBookStatus()),
                new StartDate(this.getStartDate()),
                new EndDate(this.getEndDate()),
                new ReturnDate(this.getReturnDate()),
                new BorrowStatus(this.getBorrowStatus()),
                new PenaltyFeeBoolean(this.getPenaltyFeeBoolean())
        );
    }

    public BorrowDBO fromDomain(BorrowOut borrowOut){
        return new BorrowDBO(
                borrowOut.getBorrowId().getValue(),
                borrowOut.getUserId().getValue(),
                borrowOut.getUserName().getValue(),
                borrowOut.getBookId().getValue(),
                borrowOut.getBookName().getValue(),
                borrowOut.getBookStatus().getValue(),
                borrowOut.getStartDate().getValue(),
                borrowOut.getEndDate().getValue(),
                borrowOut.getReturnDate().getValue(),
                borrowOut.getBorrowStatus().getValue(),
                borrowOut.getPenaltyFeeBoolean().getValue()
        );
    }


}
