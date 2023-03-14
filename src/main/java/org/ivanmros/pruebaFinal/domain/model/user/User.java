package org.ivanmros.pruebaFinal.domain.model.user;

public class User {

    private final UserId idUser;
    private final UserName userName;

    public User(UserId idUser, UserName userName) {
        this.idUser = idUser;
        this.userName = userName;
    }

    public UserId getIdUser() {
        return idUser;
    }

    public UserName getUserName() {
        return userName;
    }
}
