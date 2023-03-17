package org.ivanmros.pruebaFinal.domain.model.fee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ivanmros.pruebaFinal.domain.model.borrow.in.StartDate;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowId;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.EndDate;
import org.ivanmros.pruebaFinal.domain.model.fee.Fee;
import org.ivanmros.pruebaFinal.domain.model.fee.FeeAmount;
import org.ivanmros.pruebaFinal.domain.model.fee.FeeId;
import org.ivanmros.pruebaFinal.domain.model.user.UserId;
import org.ivanmros.pruebaFinal.domain.model.user.UserName;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeeDTO {

    private Integer feeId;
    private Integer userId;
    private String userName;
    private Integer borrowId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double feeAmount;

    public Fee toDomain(){
        return new Fee(
                new FeeId(this.feeId),
                new UserId(this.userId),
                new UserName(this.userName),
                new BorrowId(this.borrowId),
                new StartDate(this.startDate),
                new EndDate(this.endDate),
                new FeeAmount(this.feeAmount)
        );
    }

    public static FeeDTO fromDomain(Fee fee){
        return new FeeDTO(
                fee.getFeeId().getValue(),
                fee.getUserId().getValue(),
                fee.getUserName().getValue(),
                fee.getBorrowId().getValue(),
                fee.getStartDate().getValue(),
                fee.getEndDate().getValue(),
                fee.getFeeAmount().getValue());
    }
}
