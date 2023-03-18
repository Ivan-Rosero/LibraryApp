package org.ivanmros.pruebaFinal.domain.usecase;

import org.ivanmros.pruebaFinal.domain.model.gateway.IUserRepository;
import org.ivanmros.pruebaFinal.domain.model.user.User;
import org.ivanmros.pruebaFinal.domain.model.user.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserUseCase {
    public final IUserRepository iUserRepository;

    public UserUseCase(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public User saveUser(User user){
        return this.iUserRepository.saveUser(user);
    }

    public ArrayList<UserDTO> findAllUsers(){
        List<User> userList = this.iUserRepository.findAllUsers();
        return (ArrayList<UserDTO>) userList.stream().map(UserDTO::fromDomain).collect(Collectors.toList());
    }

    public UserDTO findUserById(String id){
        User user = this.iUserRepository.findUserById(id);
        return UserDTO.fromDomain(user);
    }
}
