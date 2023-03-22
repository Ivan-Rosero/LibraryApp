package org.ivanmros.pruebaFinal.domain.usecase;

import jakarta.persistence.EntityExistsException;
import org.ivanmros.pruebaFinal.domain.model.gateway.IUserRepository;
import org.ivanmros.pruebaFinal.domain.model.user.User;
import org.ivanmros.pruebaFinal.domain.model.user.UserId;
import org.ivanmros.pruebaFinal.domain.model.user.UserName;
import org.ivanmros.pruebaFinal.domain.model.user.dto.UserDTO;
import org.ivanmros.pruebaFinal.infraestructure.adapters.user.UserRepositoryAdapter;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserUseCaseTest {

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private IUserRepository iUserRepository;
    @Mock
    private UserRepositoryAdapter userRepositoryAdapter;

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
    @DisplayName("Save user")
    void saveUser() {
        User user = new User(
                new UserId("1234567"),
                new UserName("Ivan")
        );

        when(iUserRepository.saveUser(any(User.class)))
                .thenReturn(user);

//        when(iUserRepository.findUserById("12345678"))
//                .thenReturn(null);

        User userTest = userUseCase.saveUser(user);
        assertEquals(userTest, user);
        System.out.println("User saved name: " + user.getUserName().getValue());
        System.out.println("User saved name test: " + userTest.getUserName().getValue());

    }

    @Test
    @DisplayName("Save user Exception")
    void saveUserExcepton() {
        User user = new User(
                new UserId("1234567"),
                new UserName("Ivan")
        );

        when(iUserRepository.findUserById("1234567"))
                .thenReturn(user);

        Throwable exception = assertThrows(EntityExistsException.class, () -> userRepositoryAdapter.saveUser(user));
        assertTrue(exception.getMessage().contains("El usuario con el id ingresado ya existe."));
    }

    @Nested
    @DisplayName("Testing get user methods")
    class getUserTest{

        @Test
        @DisplayName("Get user by id test")
        void findUserByIdTest() {
            User user = new User(
                    new UserId("1234567"),
                    new UserName("Ivan")
            );

            when(iUserRepository.findUserById("1234567"))
                    .thenReturn(user);

            UserDTO userTest = userUseCase.findUserById("1234567");
            System.out.println(userTest.getUserId());
            System.out.println(user.getIdUser().getValue());
            assertEquals(userTest.getUserId(), user.getIdUser().getValue());
        }

        @Test
        void getAllUsers() {
            User user = new User(
                    new UserId("1234567"),
                    new UserName("Ivan")
            );

            User user1 = new User(
                    new UserId("12345678"),
                    new UserName("Mauricio")
            );

            List<User> userList = new ArrayList<>();
            userList.add(user);
            userList.add(user1);

            when(iUserRepository.findAllUsers())
                    .thenReturn(userList);

            List<UserDTO> userTest = userUseCase.findAllUsers();

            System.out.println("Imprimiendo tamaño de lista");
            System.out.println(userTest.size());

            assertEquals(2, userTest.size());
        }
    }
}
