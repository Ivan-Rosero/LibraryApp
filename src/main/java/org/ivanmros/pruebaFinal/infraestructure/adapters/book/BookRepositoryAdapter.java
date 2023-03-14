package org.ivanmros.pruebaFinal.infraestructure.adapters.book;

import lombok.AllArgsConstructor;
import org.ivanmros.pruebaFinal.domain.model.book.Book;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBookRepository;
import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.BookDBO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookRepositoryAdapter implements IBookRepository {
    private final IBookRepositoryAdapter iBookRepositoryAdapter;

    @Override
    public Book saveBook(Book book) {
        Optional<BookDBO> bookDBO = iBookRepositoryAdapter.findById(book.getIdBook().getValue());
        if (bookDBO.isPresent()) {
            throw new NullPointerException("El libro con el id: " + book.getIdBook().getValue() + " ya existe.");
        } else {
            BookDBO bookSaved = iBookRepositoryAdapter.save(BookDBO.fromDomain(book));
            return BookDBO.toDomain(bookSaved);
        }
    }

    @Override
    public List<Book> findAllBooks() {
        return iBookRepositoryAdapter.findAll().stream().map(BookDBO::toDomain).collect(Collectors.toList());
    }

    @Override
    public Book findBookById(Integer id) {
        Optional<BookDBO> bookDBO = iBookRepositoryAdapter.findById(id);
        if (bookDBO.isEmpty()){
            throw new NullPointerException("No existe el libro con el id: " + id);
        }else{
            return BookDBO.toDomain(bookDBO.get());
        }
    }

    @Override
    public Book updateBook(Book book) {
        BookDBO bookDBO = BookDBO.fromDomain(book);
        Optional<BookDBO> bookFound = iBookRepositoryAdapter.findById(bookDBO.getIdBook());
        if(bookFound.isEmpty()){
            throw new NullPointerException("No existe libro con ese id: " + book.getIdBook().getValue());
        }else {
            BookDBO bookSaved = iBookRepositoryAdapter.save(bookDBO);
            return BookDBO.toDomain(bookSaved);
        }
    }
}
