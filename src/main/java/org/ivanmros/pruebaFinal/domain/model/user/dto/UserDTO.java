package org.ivanmros.pruebaFinal.domain.model.user.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ivanmros.pruebaFinal.domain.model.user.User;
import org.ivanmros.pruebaFinal.domain.model.user.UserId;
import org.ivanmros.pruebaFinal.domain.model.user.UserName;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    //@NotNull
    private Integer userId;
    @NotNull
    @NotEmpty(message = "Escriba su nombre")
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
