package org.ivanmros.pruebaFinal.domain.usecase;

import org.ivanmros.pruebaFinal.domain.model.book.Book;
import org.ivanmros.pruebaFinal.domain.model.book.BookId;
import org.ivanmros.pruebaFinal.domain.model.book.BookName;
import org.ivanmros.pruebaFinal.domain.model.book.BookStatus;
import org.ivanmros.pruebaFinal.domain.model.book.dto.BookDTO;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class BookUseCaseTest {
    @InjectMocks
    private BookUseCase bookUseCase;

    @Mock
    private IBookRepository iBookRepository;

//    @Test
//    @DisplayName("Save book")
//    public void saveBook() throws Exception {
//
//        Book book = new Book(
//                new BookId(1),
//                new BookName("Vicious"),
//                new BookStatus(true));
//
//
//        when(iBookRepository.saveBook((any(Book.class)))).thenReturn(book);
//
//        Book actualBook = bookUseCase.saveBook(book);
//        Assertions.assertNotNull(actualBook);
//        assertEquals("test result", actualBook.getBookName().getValue(), "Vicious");
//    }
}
