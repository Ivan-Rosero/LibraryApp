package org.ivanmros.pruebaFinal.domain.model.borrow.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowOut;
import org.ivanmros.pruebaFinal.domain.usecase.utils.Constants;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowOutDTO {

    private Integer borrowId;
    @NotNull
    private Integer userId;

    private String userName;
    @NotNull
    private Integer bookId;

    private String bookName;

    private Boolean bookStatus;
    @NotNull(message = "Este campo no debe estar vacío")
    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)$", message = "La fecha no cumple con el patrón establecido dd/mm/aaaa")
    private String startDate;
    private String endDate;

    private String returnDate;

    private Boolean borrowStatus;

    private Boolean penaltyFeeBoolean;

    public static BorrowOutDTO fromDomain(BorrowOut borrowOut){
        return new BorrowOutDTO(borrowOut.getBorrowId().getValue(),
                borrowOut.getUserId().getValue(),
                borrowOut.getUserName().getValue(),
                borrowOut.getBookId().getValue(),
                borrowOut.getBookName().getValue(),
                borrowOut.getBookStatus().getValue(),
                borrowOut.getStartDate().getValue().format(Constants.FORMATTER),
                borrowOut.getEndDate().getValue().format(Constants.FORMATTER),
                borrowOut.getReturnDate().getValue().format(Constants.FORMATTER),
                borrowOut.getBorrowStatus().getValue(),
                borrowOut.getPenaltyFeeBoolean().getValue()
        );
    }
}
