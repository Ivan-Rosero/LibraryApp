package org.ivanmros.pruebaFinal.domain.usecase;

import org.ivanmros.pruebaFinal.domain.model.book.Book;
import org.ivanmros.pruebaFinal.domain.model.book.BookId;
import org.ivanmros.pruebaFinal.domain.model.book.BookName;
import org.ivanmros.pruebaFinal.domain.model.book.BookStatus;
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

    public BookDTO saveBook(BookDTO bookDTO){
        Book book = new Book(
                new BookId(bookDTO.getIdBook()),
                new BookName(bookDTO.getBookName()),
                new BookStatus(true)
        );
        return BookDTO.fromDomain(this.iBookRepository.saveBook(book));
    }

    public ArrayList<BookDTO> findAllBooks(){
        List<Book> bookList = this.iBookRepository.findAllBooks();
        return (ArrayList<BookDTO>) bookList.stream().map(BookDTO::fromDomain).collect(Collectors.toList());
    }

    public ArrayList<BookDTO> findAvailableBooks(){
        List<Book> bookList = this.iBookRepository.findAllBooks();
        List<Book> availableBooks = bookList.stream()
                .filter(b -> b.getBookStatus().getValue() == true)
                .collect(Collectors.toList());
        return (ArrayList<BookDTO>) availableBooks.stream().map(BookDTO::fromDomain).collect(Collectors.toList());
    }

    public ArrayList<BookDTO> findBorrowedBooks(){
        List<Book> bookList = this.iBookRepository.findAllBooks();
        List<Book> borrowedBooks = bookList.stream()
                .filter(b -> b.getBookStatus().getValue() == false)
                .collect(Collectors.toList());
        return (ArrayList<BookDTO>) borrowedBooks.stream().map(BookDTO::fromDomain).collect(Collectors.toList());
    }

    public BookDTO findBookById(Integer idBook){
        Book book = this.iBookRepository.findBookById(idBook);
        return BookDTO.fromDomain(book);
    }
}
