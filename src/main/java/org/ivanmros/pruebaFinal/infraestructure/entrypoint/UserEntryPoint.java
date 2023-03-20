package org.ivanmros.pruebaFinal.infraestructure.entrypoint;


import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.ivanmros.pruebaFinal.domain.model.user.User;
import org.ivanmros.pruebaFinal.domain.model.user.dto.UserDTO;
import org.ivanmros.pruebaFinal.domain.usecase.UserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class UserEntryPoint {

    private final UserUseCase userUseCase;

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO userDTO){
        try{
            User user = UserDTO.toDomain(userDTO);
            UserDTO userDTO1 = UserDTO.fromDomain(userUseCase.saveUser(user));
            return new ResponseEntity(userDTO1, HttpStatus.CREATED);
        }catch(NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }catch(IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }catch (EntityExistsException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers(){
        List<UserDTO> users = userUseCase.findAllUsers();
        try{
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch(NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable(name = "userId") String userId){
        try{
            userId.equals(userId);
            return new ResponseEntity(userUseCase.findUserById(userId), HttpStatus.OK);
        }catch(NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
