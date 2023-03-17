package org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo;

import jakarta.persistence.*;
import lombok.*;
import org.ivanmros.pruebaFinal.domain.model.book.BookId;
import org.ivanmros.pruebaFinal.domain.model.book.BookName;
import org.ivanmros.pruebaFinal.domain.model.book.BookStatus;
import org.ivanmros.pruebaFinal.domain.model.borrow.in.StartDate;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.*;
import org.ivanmros.pruebaFinal.domain.model.fee.Fee;
import org.ivanmros.pruebaFinal.domain.model.fee.FeeAmount;
import org.ivanmros.pruebaFinal.domain.model.fee.FeeId;
import org.ivanmros.pruebaFinal.domain.model.user.UserId;
import org.ivanmros.pruebaFinal.domain.model.user.UserName;

import java.time.LocalDate;

@Entity
@Table(name="fee")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeeDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FEE")
    private Integer feeId;
    @Column(name = "ID_USER")
    private Integer userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "ID_BORROW")
    private Integer borrowId;
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "END_DATE")
    private LocalDate endDate;
    @Column(name = "FEE_AMOUNT")
    private Double feeAmount;

//    public FeeDBO(Integer feeId, Integer userId, String userName, Integer borrowId, LocalDate startDate, LocalDate endDate, Double feeAmount) {
//        this.feeId = feeId;
//        this.userId = userId;
//        this.userName = userName;
//        this.borrowId = borrowId;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.feeAmount = feeAmount;
//    }

    public Fee toDomain(){
        return new Fee(
                new FeeId(this.getFeeId()),
                new UserId(this.getUserId()),
                new UserName(this.getUserName()),
                new BorrowId(this.getBorrowId()),
                new StartDate(this.getStartDate()),
                new EndDate(this.getEndDate()),
                new FeeAmount(this.getFeeAmount())
        );
    }

    public FeeDBO fromDomain(Fee fee){
        return new FeeDBO(
                fee.getFeeId().getValue(),
                fee.getUserId().getValue(),
                fee.getUserName().getValue(),
                fee.getBorrowId().getValue(),
                fee.getStartDate().getValue(),
                fee.getEndDate().getValue(),
                fee.getFeeAmount().getValue()
        );
    }
}
