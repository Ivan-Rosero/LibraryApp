package org.ivanmros.pruebaFinal.domain.model.gateway;

import org.ivanmros.pruebaFinal.domain.model.user.User;

import java.util.List;

public interface IUserRepository {

    User saveUser(User user);
    List<User> findAllUsers();

    User findUserById(String id);
}
