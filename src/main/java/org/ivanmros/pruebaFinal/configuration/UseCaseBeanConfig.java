package org.ivanmros.pruebaFinal.configuration;

import org.ivanmros.pruebaFinal.domain.model.gateway.IBookRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBorrowRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IFeeRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IUserRepository;
import org.ivanmros.pruebaFinal.domain.usecase.BookUseCase;
import org.ivanmros.pruebaFinal.domain.usecase.BorrowUseCase;
import org.ivanmros.pruebaFinal.domain.usecase.FeeUseCase;
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

    @Bean("borrowService")
    public BorrowUseCase borrowUseCase (IBookRepository iBookRepository, IUserRepository iUserRepository, IBorrowRepository iBorrowRepository, IFeeRepository iFeeRepository){
        return new BorrowUseCase(iBookRepository, iUserRepository, iBorrowRepository, iFeeRepository);
    }

    @Bean("feeService")
    public FeeUseCase feeUseCase (IBorrowRepository iBorrowRepository, IFeeRepository iFeeRepository, IUserRepository iUserRepository){
        return new FeeUseCase(iBorrowRepository, iFeeRepository, iUserRepository);
    }
}
