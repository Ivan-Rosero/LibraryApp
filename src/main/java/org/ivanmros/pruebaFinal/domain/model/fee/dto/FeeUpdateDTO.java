package org.ivanmros.pruebaFinal.domain.model.fee.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeeUpdateDTO {
    @NotNull(message = "El id no puede ser nulo")
    private Integer userId;

    private Double feeAmount;


}
