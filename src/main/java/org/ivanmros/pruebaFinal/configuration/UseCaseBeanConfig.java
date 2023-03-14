package org.ivanmros.pruebaFinal.configuration;

import org.ivanmros.pruebaFinal.domain.model.gateway.IBookRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IUserRepository;
import org.ivanmros.pruebaFinal.domain.usecase.BookUseCase;
import org.ivanmros.pruebaFinal.domain.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {

    @Bean("bookService")
    public BookUseCase bookUseCase(IBookRepository iBookRepository){
        return new BookUseCase(iBookRepository);
    }

    @Bean("userService")
    public UserUseCase userUseCase (IUserRepository iUserRepository){
        return new UserUseCase(iUserRepository);
    }
}
