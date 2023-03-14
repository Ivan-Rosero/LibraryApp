package org.ivanmros.pruebaFinal.domain.usecase;

import org.ivanmros.pruebaFinal.domain.model.borrow.dto.BorrowOutDTO;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBookRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBorrowRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IUserRepository;

public class BorrowUseCase {

    private final IBookRepository iBookRepository;
    private final IUserRepository iUserRepository;
    private final IBorrowRepository iBorrowRepository;

    public BorrowUseCase(IBookRepository iBookRepository, IUserRepository iUserRepository,
                         IBorrowRepository iBorrowRepository) {
        this.iBookRepository = iBookRepository;
        this.iUserRepository = iUserRepository;
        this.iBorrowRepository = iBorrowRepository;
    }

    public BorrowOutDTO createBorrow(BorrowOutDTO borrowOutDTO){

    }
}
