package org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.ivanmros.pruebaFinal.domain.model.book.BookId;
import org.ivanmros.pruebaFinal.domain.model.book.BookName;
import org.ivanmros.pruebaFinal.domain.model.book.BookStatus;
import org.ivanmros.pruebaFinal.domain.model.borrow.in.StartDate;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowId;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowOut;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowStatus;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.EndDate;
import org.ivanmros.pruebaFinal.domain.model.user.UserId;
import org.ivanmros.pruebaFinal.domain.model.user.UserName;

import java.time.LocalDate;

@Entity
@Table(name="borrow")
@Builder
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
    private Integer userId;
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
    @Column(name = "BORROW_STATUS")
    private Boolean borrowStatus;

    public static BorrowOut toDomain(BorrowDBO borrowDBO){
        return new BorrowOut(
                new BorrowId(borrowDBO.getBorrowId()),
                new UserId(borrowDBO.getUserId()),
                new UserName(borrowDBO.getUserName()),
                new BookId(borrowDBO.getBookId()),
                new BookName(borrowDBO.getBookName()),
                new BookStatus(borrowDBO.getBookStatus()),
                new StartDate(borrowDBO.getStartDate()),
                new EndDate(borrowDBO.getEndDate()),
                new BorrowStatus(borrowDBO.getBorrowStatus())
        );
    }

    public static BorrowDBO fromDomain(BorrowOut borrowOut){
        return new BorrowDBO(
                borrowOut.getBorrowId().getValue(),
                borrowOut.getUserId().getValue(),
                borrowOut.getUserName().getValue(),
                borrowOut.getBookId().getValue(),
                borrowOut.getBookName().getValue(),
                borrowOut.getBookStatus().getValue(),
                borrowOut.getStartDate().getValue(),
                borrowOut.getEndDate().getValue(),
                borrowOut.getBorrowStatus().getValue()
        );
    }
}
