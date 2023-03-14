package org.ivanmros.pruebaFinal.infraestructure.adapters.user;

import lombok.AllArgsConstructor;
import org.ivanmros.pruebaFinal.domain.model.gateway.IUserRepository;
import org.ivanmros.pruebaFinal.domain.model.user.User;
import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.UserDBO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserRepositoryAdapter implements IUserRepository{

    private final IUserRepositoryAdapter iUserRepositoryAdapter;


    @Override
    public User saveUser(User user) {
        Optional<UserDBO> userDBO = iUserRepositoryAdapter.findById(user.getIdUser().getValue());
        if (userDBO.isPresent()) {
            throw new NullPointerException("El usuario con el id: " + user.getIdUser().getValue() + " ya existe.");
        } else {
            UserDBO userSaved = iUserRepositoryAdapter.save(UserDBO.fromDomain(user));
            return UserDBO.toDomain(userSaved);
        }
    }

    @Override
    public List<User> findAllUsers() {
        return iUserRepositoryAdapter.findAll().stream().map(UserDBO::toDomain).collect(Collectors.toList());
    }

    @Override
    public User findUserById(Integer id) {
        Optional<UserDBO> userDBO = iUserRepositoryAdapter.findById(id);
        if (userDBO.isEmpty()){
            throw new NullPointerException("No existe el usuario con el id: " + id);
        }else{
            return UserDBO.toDomain(userDBO.get());
        }
    }

    @Override
    public User updateUser(User user) {
        UserDBO userDBO = UserDBO.fromDomain(user);
        Optional<UserDBO> userFound = iUserRepositoryAdapter.findById(userDBO.getUserId());
        if(userFound.isEmpty()){
            throw new NullPointerException("No existe usuario con ese id: " + user.getIdUser().getValue());
        }else {
            UserDBO userSaved = iUserRepositoryAdapter.save(userDBO);
            return UserDBO.toDomain(userSaved);
        }
    }
}
