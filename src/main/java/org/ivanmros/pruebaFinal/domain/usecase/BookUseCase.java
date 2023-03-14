package org.ivanmros.pruebaFinal.domain.usecase;

import org.ivanmros.pruebaFinal.domain.model.book.Book;
import org.ivanmros.pruebaFinal.domain.model.book.dto.BookDTO;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookUseCase {

    private final IBookRepository iBookRepository;

    public BookUseCase(IBookRepository iBookRepository) {
        this.iBookRepository = iBookRepository;
    }

    public Book saveBook(Book book){
        return this.iBookRepository.saveBook(book);
    }

    public ArrayList<BookDTO> findAllBooks(){
        List<Book> bookList = this.iBookRepository.findAllBooks();
        return (ArrayList<BookDTO>) bookList.stream().map(BookDTO::fromDomain).collect(Collectors.toList());
    }

    public BookDTO findBookById(Integer idBook){
        Book book = this.iBookRepository.findBookById(idBook);

        return BookDTO.fromDomain(book);
    }
}
