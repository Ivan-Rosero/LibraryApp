package org.ivanmros.pruebaFinal.domain.model.borrow.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ivanmros.pruebaFinal.domain.model.book.BookId;
import org.ivanmros.pruebaFinal.domain.model.borrow.in.BorrowIn;
import org.ivanmros.pruebaFinal.domain.model.borrow.in.StartDate;
import org.ivanmros.pruebaFinal.domain.model.user.UserId;
import jakarta.validation.constraints.NotNull;
import org.ivanmros.pruebaFinal.domain.usecase.utils.Constants;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowInDTO {
    @NotNull
    private Integer bookId;

    @NotNull(message = "Este campo no debe estar vacío")
    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)$", message = "La fecha no cumple con el patrón establecido dd/mm/aaaa")
    private  String startDate;
    @NotNull
    private Integer userId;

    public static BorrowIn toDomain(BorrowInDTO borrowInDTO){
        return new BorrowIn(
                new BookId(borrowInDTO.getBookId()),
                new StartDate(LocalDate.parse(borrowInDTO.getStartDate(), Constants.FORMATTER)),
                new UserId(borrowInDTO.getUserId())
        );
    }


}
