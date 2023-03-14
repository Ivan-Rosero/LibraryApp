package org.ivanmros.pruebaFinal.domain.model.gateway;

import org.ivanmros.pruebaFinal.domain.model.user.User;

import java.util.List;

public interface IUserRepository {

    public User saveUser(User user);
    public List<User> findAllUsers();

    public User findUserById(Integer id);
    public User updateUser(User user);
}
