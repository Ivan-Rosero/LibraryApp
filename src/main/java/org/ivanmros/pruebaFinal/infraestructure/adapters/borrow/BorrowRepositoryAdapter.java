package org.ivanmros.pruebaFinal.infraestructure.adapters.borrow;

import lombok.AllArgsConstructor;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowOut;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBorrowRepository;
import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.BorrowDBO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BorrowRepositoryAdapter implements IBorrowRepository {

    private final IBorrowRepositoryAdapter iBorrowRepositoryAdapter;

    @Override
    public BorrowOut createBorrow(BorrowOut borrowOut) {
        BorrowDBO borrowCreated = iBorrowRepositoryAdapter.save(BorrowDBO.fromDomain(borrowOut));
        return BorrowDBO.toDomain(borrowCreated);
    }

    @Override
    public List<BorrowOut> findByUserId(Integer userId) {
        List<BorrowDBO> borrowedByUserList = iBorrowRepositoryAdapter.findByUserId(userId);
        if(borrowedByUserList.isEmpty()){
            throw new NullPointerException("El usuario con id " + userId + " no ha solicitado prestamos aún.");
        }
        return borrowedByUserList.stream().map(BorrowDBO::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<BorrowOut> findAllBorrows() {
        List<BorrowDBO> borrowList = this.iBorrowRepositoryAdapter.findAll();
        if(borrowList.isEmpty()){
            throw new IllegalArgumentException("No hay registros de prestamos.");
        }
        return iBorrowRepositoryAdapter.findAll().stream().map(BorrowDBO::toDomain).collect(Collectors.toList());
    }
}
