package org.ivanmros.pruebaFinal.domain.model.gateway;

import org.ivanmros.pruebaFinal.domain.model.book.Book;

import java.util.ArrayList;
import java.util.List;

public interface IBookRepository {

    Book saveBook(Book book);
    List<Book> findAllBooks();

    Book findBookById(Integer id);
    Book updateBook(Book book);

    List<Book> findAvailableBooks();
    List<Book> findBorrowedBooks();

}
