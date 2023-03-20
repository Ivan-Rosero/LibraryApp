package org.ivanmros.pruebaFinal.domain.usecase;

import jakarta.persistence.EntityExistsException;
import org.ivanmros.pruebaFinal.domain.model.book.Book;
import org.ivanmros.pruebaFinal.domain.model.book.BookId;
import org.ivanmros.pruebaFinal.domain.model.book.BookName;
import org.ivanmros.pruebaFinal.domain.model.book.BookStatus;
import org.ivanmros.pruebaFinal.domain.model.borrow.dto.BorrowInDTO;
import org.ivanmros.pruebaFinal.domain.model.borrow.in.BorrowIn;
import org.ivanmros.pruebaFinal.domain.model.borrow.in.StartDate;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.*;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBookRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBorrowRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IUserRepository;
import org.ivanmros.pruebaFinal.domain.model.user.User;
import org.ivanmros.pruebaFinal.domain.model.user.UserId;
import org.ivanmros.pruebaFinal.domain.model.user.UserName;
import org.ivanmros.pruebaFinal.domain.usecase.utils.Constants;
import org.ivanmros.pruebaFinal.domain.usecase.utils.Functions;
import org.ivanmros.pruebaFinal.infraestructure.adapters.borrow.BorrowRepositoryAdapter;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BorrowUseCaseTest {

    @InjectMocks
    private BorrowUseCase borrowUseCase;

    @Mock
    private IBorrowRepository iBorrowRepository;
    @Mock
    private IBookRepository iBookRepository;
    @Mock
    private IUserRepository iUserRepository;
    @Mock
    private BorrowRepositoryAdapter borrowRepositoryAdapter;

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

    @Test
    @DisplayName("Test of saving borrow")
    void saveBorrow() {
        User user = new User(
                new UserId("1234567"),
                new UserName("Ivan")
        );

        Book book = new Book(
                new BookId(1),
                new BookName("Perfect"),
                new BookStatus(true));

        BorrowOut borrow = new BorrowOut(
                new BorrowId(1),
                user.getIdUser(),
                user.getUserName(),
                book.getIdBook(),
                book.getBookName(),
                book.getBookStatus(),
                new StartDate(LocalDate.of(2023,03,19)),
                new EndDate(Functions.endDateFunction(LocalDate.of(2023,03,19))),
                new ReturnDate(LocalDate.of(2020,01,01)),
                new BorrowStatus(true),
                new PenaltyFeeBoolean(false)
        );

        BorrowInDTO borrowInDTO = new BorrowInDTO(
               1,
                "18/03/2023",
                "1234567"
        );

        BorrowIn borrowIn = borrowInDTO.toDomain();

        when(iBookRepository.findBookById(any(Integer.class)))
                .thenReturn(book);

        when(iUserRepository.findUserById(any(String.class)))
                .thenReturn(user);

        when(iBorrowRepository.createBorrow(any(BorrowOut.class)))
                .thenReturn(borrow);

        //BorrowOut borrowR = borrowUseCase.createBorrow(borrowOutDTO);

    }

    @Test
    @DisplayName("Find borrow by user id error")
    void findBorrowNotFound() {
        when(iBorrowRepository.findByUserId(any(String.class)))
                .thenReturn(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            borrowRepositoryAdapter.findByUserId("123456789876");
        });

        assertEquals("No existe el usuario con el id ingresado", exception.getMessage());
    }
}
