package org.ivanmros.pruebaFinal.domain.model.user.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.ivanmros.pruebaFinal.domain.model.user.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    @NotNull(message = "Este campo no debe estar vacío")
    @NotEmpty(message = "Este campo no debe estar vacío")
    @Length(min = 7, max = 15, message = "La cédula debe tener mínimo 7 dígitos y máximo 15")
    @Pattern(regexp = "\\d+", message = "Ingrese solo números")
    private String userId;
    @NotNull(message = "Este campo no debe estar vacío")
    @NotEmpty(message = "Escriba su nombre por favor")
    @Length(min = 3, max = 50, message = "El nombre debe incluir mínimo 3 caracteres y máximo 50")
    @Pattern(regexp = "[\\p{L}\\s]+", message = "Ingrese nombre con caracteres válidos")
    private String userName;

    public static User toDomain(UserDTO userDTO){
        return new User(
                new UserId(userDTO.getUserId()),
                new UserName(userDTO.getUserName())
        );
    }

    public static UserDTO fromDomain(User user){
        return new UserDTO(user.getIdUser().getValue(),
                user.getUserName().getValue());
    }
}
