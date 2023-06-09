package org.ivanmros.pruebaFinal.infraestructure.entrypoint;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.ivanmros.pruebaFinal.domain.model.book.dto.BookDTO;
import org.ivanmros.pruebaFinal.domain.usecase.BookUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class BookEntryPoint {

    private final BookUseCase bookUseCase;

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody BookDTO bookDTO){
        try{
            return new ResponseEntity(bookUseCase.saveBook(bookDTO), HttpStatus.CREATED);
        }catch(NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }catch(IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllBooks(){
        List<BookDTO> books = bookUseCase.findAllBooks();
        try{
            return new ResponseEntity<>(books, HttpStatus.OK);
        }catch(NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableBooks(){
        List<BookDTO> books = bookUseCase.findAvailableBooks();
        try{
            return new ResponseEntity<>(books, HttpStatus.OK);
        }catch(NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/borrowed")
    public ResponseEntity<?> getBorrowedBooks(){
        List<BookDTO> books = bookUseCase.findBorrowedBooks();
        try{
            return new ResponseEntity<>(books, HttpStatus.OK);
        }catch(NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idBook}")
    public ResponseEntity<?> getById(@PathVariable(name = "idBook") Integer idBook){
        try{
            idBook.equals(idBook);
            return new ResponseEntity(bookUseCase.findBookById(idBook), HttpStatus.OK);
        }catch(NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }




}