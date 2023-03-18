package org.ivanmros.pruebaFinal.domain.model.borrow.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowUpdateDTO {

    @NotNull
    //@Pattern(regexp = "\\d+", message = "Ingrese solo números")
    private Integer borrowId;

}
