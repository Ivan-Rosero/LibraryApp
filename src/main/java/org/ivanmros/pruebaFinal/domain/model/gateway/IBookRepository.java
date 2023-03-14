package org.ivanmros.pruebaFinal.domain.model.gateway;

import org.ivanmros.pruebaFinal.domain.model.book.Book;

import java.util.List;

public interface IBookRepository {

    public Book saveBook(Book book);
    public List<Book> findAllBooks();

    public Book findBookById(Integer id);
    public Book updateBook(Book book);

}
