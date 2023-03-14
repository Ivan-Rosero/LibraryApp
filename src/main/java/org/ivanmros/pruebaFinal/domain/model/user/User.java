package org.ivanmros.pruebaFinal.domain.model.user;

public class User {

    private final UserId userId;
    private final UserName userName;

    public User(UserId userId, UserName userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserId getIdUser() {
        return userId;
    }

    public UserName getUserName() {
        return userName;
    }
}
