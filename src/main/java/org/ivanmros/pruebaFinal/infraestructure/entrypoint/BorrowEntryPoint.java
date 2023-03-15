package org.ivanmros.pruebaFinal.infraestructure.entrypoint;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.ivanmros.pruebaFinal.domain.model.borrow.dto.BorrowOutDTO;
import org.ivanmros.pruebaFinal.domain.usecase.BorrowUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class BorrowEntryPoint {

    private final BorrowUseCase borrowUseCase;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid BorrowOutDTO borrowOutDTO){
        try{
            return new ResponseEntity<>(borrowUseCase.createBorrow(borrowOutDTO), HttpStatus.CREATED);
        }catch (NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getBorrowByUserId(@PathVariable(name = "userId") Integer userId){
        try{
            List<BorrowOutDTO> listBorrowsByUser = borrowUseCase.findByUserId(userId);
            return new ResponseEntity<>(listBorrowsByUser, HttpStatus.OK);
        }catch(NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBorrows(){
        List<BorrowOutDTO> allBorrowsList = borrowUseCase.findAllBorrows();
        try{
            return new ResponseEntity<>(allBorrowsList, HttpStatus.OK);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
