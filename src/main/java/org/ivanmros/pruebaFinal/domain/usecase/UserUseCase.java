package org.ivanmros.pruebaFinal.domain.usecase;

import jakarta.persistence.EntityExistsException;
import org.ivanmros.pruebaFinal.domain.model.gateway.IUserRepository;
import org.ivanmros.pruebaFinal.domain.model.user.User;
import org.ivanmros.pruebaFinal.domain.model.user.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserUseCase {
    public final IUserRepository iUserRepository;

    public UserUseCase(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public User saveUser(User user){
        return this.iUserRepository.saveUser(user);
    }
//    public User saveUser(User user) throws EntityExistsException {
//        Optional<User> opt = Optional.ofNullable(iUserRepository.findUserById(user.getIdUser().getValue()));
//        if(opt.isPresent()){
//            throw new EntityExistsException("El usuario con el id ingresado ya existe.");
//        }
//
//        User userR = iUserRepository.saveUser(user);
//
//        return userR;
//}

    public ArrayList<UserDTO> findAllUsers(){
        List<User> userList = this.iUserRepository.findAllUsers();
        return (ArrayList<UserDTO>) userList.stream().map(UserDTO::fromDomain).collect(Collectors.toList());
    }

    public UserDTO findUserById(String id){
        User user = this.iUserRepository.findUserById(id);
        return UserDTO.fromDomain(user);
    }
}
