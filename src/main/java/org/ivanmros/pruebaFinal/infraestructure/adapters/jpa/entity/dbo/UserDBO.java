package org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo;

import jakarta.persistence.*;
import lombok.*;
import org.ivanmros.pruebaFinal.domain.model.user.User;
import org.ivanmros.pruebaFinal.domain.model.user.UserId;
import org.ivanmros.pruebaFinal.domain.model.user.UserName;

@Entity
@Table(name="user")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDBO {

    @Id
    @Column(name = "ID_USER")
    private String userId;
    @Column(name = "USER_NAME")
    private String userName;

    public static User toDomain(UserDBO userDBO){
        return new User(
                new UserId(userDBO.getUserId()),
                new UserName(userDBO.getUserName())
        );
    }

    public static UserDBO fromDomain(User user){
        return new UserDBO(
                user.getIdUser().getValue(),
                user.getUserName().getValue()
        );
    }
}
