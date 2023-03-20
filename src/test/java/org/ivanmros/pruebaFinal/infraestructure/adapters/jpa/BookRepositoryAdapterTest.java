package org.ivanmros.pruebaFinal.infraestructure.adapters.jpa;

import org.ivanmros.pruebaFinal.domain.model.book.*;
import org.ivanmros.pruebaFinal.infraestructure.adapters.book.BookRepositoryAdapter;
import org.ivanmros.pruebaFinal.infraestructure.adapters.book.IBookRepositoryAdapter;
import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.BookDBO;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class BookRepositoryAdapterTest {

    @InjectMocks
    private BookRepositoryAdapter bookRepositoryAdapter;
    @Autowired
    IBookRepositoryAdapter iBookRepositoryAdapter;

    @BeforeAll
    void beforeAll() {
        bookRepositoryAdapter = new BookRepositoryAdapter(iBookRepositoryAdapter);
    }

    @Test
    @DisplayName("Find book by id")
    void findBookById() {
        Book book = new Book(
                new BookId(1),
                new BookName("Heartless"),
                new BookStatus(true)
        );

        BookDBO bookDBO = new BookDBO().fromDomain(book);

        iBookRepositoryAdapter.save(bookDBO);

        Book bookR = bookRepositoryAdapter.findBookById(1);

        assertEquals(bookR.getClass(), book.getClass());
    }

    @Test
    @DisplayName("Save book")
    void saveBook() {
        Book book = new Book(
                new BookId(1),
                new BookName("Killer"),
                new BookStatus(true)
        );

        BookDBO bookDBO = new BookDBO().fromDomain(book);

        iBookRepositoryAdapter.save(bookDBO);

        Book bookR = bookRepositoryAdapter.saveBook(book);
        assertEquals(bookR.getBookName().getValue(), book.getBookName().getValue());
    }
}
