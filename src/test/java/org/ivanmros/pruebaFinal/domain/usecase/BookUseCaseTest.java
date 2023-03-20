package org.ivanmros.pruebaFinal.domain.usecase;

import jakarta.persistence.EntityExistsException;
import org.ivanmros.pruebaFinal.PruebaFinalApplication;
import org.ivanmros.pruebaFinal.domain.model.book.Book;
import org.ivanmros.pruebaFinal.domain.model.book.BookId;
import org.ivanmros.pruebaFinal.domain.model.book.BookName;
import org.ivanmros.pruebaFinal.domain.model.book.BookStatus;
import org.ivanmros.pruebaFinal.domain.model.book.dto.BookDTO;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBookRepository;
import org.ivanmros.pruebaFinal.infraestructure.adapters.book.BookRepositoryAdapter;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(classes = PruebaFinalApplication.class)
@ContextConfiguration(classes = {BookRepositoryAdapter.class})
public class BookUseCaseTest {
    @InjectMocks
    private BookUseCase bookUseCase;

    @Mock
    private IBookRepository iBookRepository;

    private TestInfo testInfo;
    private TestReporter testReporter;

    @BeforeEach
    void setUp(TestInfo testInfo, TestReporter testReporter) {
        this.testInfo = testInfo;
        this.testReporter = testReporter;

        testReporter.publishEntry("Ejecutando: " + testInfo.getDisplayName() + " " + testInfo.getTestMethod().orElse(null).getName()
                + " con las etiquetas " + testInfo.getTags());
    }

    @AfterEach
    void tearDown() {
        System.out.println("finalizando el metodo de prueba.");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("inicializando el test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("finalizando el test");
    }

    @Nested
    @DisplayName("Testing save book method")
    class SaveBookTests {
        @Test
        @DisplayName("Save book")
        void saveBook() {

            Book book = new Book(
                    new BookId(1),
                    new BookName("Vicious"),
                    new BookStatus(true));


            when(iBookRepository.saveBook((any(Book.class)))).thenReturn(book);

            BookDTO actualBook = bookUseCase.saveBook(BookDTO.fromDomain(book));
            Assertions.assertNotNull(actualBook);
            assertEquals("test result", actualBook.getBookName(), "Vicious");

        }
    }

    @Nested
    @DisplayName("Testing get book methods")
    class GetBookTest {

        @Test
        @DisplayName("Get book by id")
        void findBookByIdTest() {
            Book book = new Book(
                    new BookId(1),
                    new BookName("Toxic"),
                    new BookStatus(true));

            when(iBookRepository.findBookById(1))
                    .thenReturn(book);

            BookDTO bookTest = bookUseCase.findBookById(1);
            assertEquals("Getting book", book.getIdBook().getValue(), bookTest.getIdBook());
            System.out.println("Book saved name: " + book.getBookName().getValue());
            System.out.println("Book searched name: " + bookTest.getBookName());
        }

        @Test
        @DisplayName("Get available books")
        void findAvailableBookTest() {
            Book book = new Book(
                    new BookId(1),
                    new BookName("Stunning"),
                    new BookStatus(true));


            when(iBookRepository.findAllBooks())
                    .thenReturn(List.of(book));


            List<BookDTO> bookTest = bookUseCase.findAvailableBooks();

            assertEquals("Getting book availability", true, bookTest.get(0).isBookStatus());
            System.out.println("Book saved name: " + book.getBookName().getValue());
            System.out.println("Book searched name: " + bookTest.get(0).getBookName());
        }

        @Test
        @DisplayName("Get borrowed books")
        void findBorrowedBookTest() {
            Book book = new Book(
                    new BookId(1),
                    new BookName("Twisted"),
                    new BookStatus(false));


            when(iBookRepository.findAllBooks())
                    .thenReturn(List.of(book));


            List<BookDTO> bookTest = bookUseCase.findBorrowedBooks();

            assertEquals("Getting book availability", false, bookTest.get(0).isBookStatus());
            System.out.println("Book saved name: " + book.getBookName().getValue());
            System.out.println("Book searched name: " + bookTest.get(0).getBookName());
        }

    }

}
