package org.ivanmros.pruebaFinal.infraestructure.adapters.borrow;

import lombok.AllArgsConstructor;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowOut;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBorrowRepository;
import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.BorrowDBO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BorrowRepositoryAdapter implements IBorrowRepository {

    private final IBorrowRepositoryAdapter iBorrowRepositoryAdapter;

    @Override
    public BorrowOut createBorrow(BorrowOut borrowOut) {
        return iBorrowRepositoryAdapter.save(new BorrowDBO().fromDomain(borrowOut)).toDomain();
    }

    @Override
    public List<BorrowOut> findByUserId(String userId) {
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


    //Editar desde aquí

    @Override
    public BorrowOut updateBorrow(BorrowOut borrowOut) {

        return iBorrowRepositoryAdapter.save(new BorrowDBO().fromDomain(borrowOut)).toDomain();
    }

    @Override
    public BorrowOut findByBookId(Integer bookId) {
        Optional<BorrowDBO> obj = Optional.ofNullable(iBorrowRepositoryAdapter.findBorrowByBookId(bookId));
        return obj.map(BorrowDBO::toDomain).orElse(null);
    }

    @Override
    public BorrowOut findById(Integer borrowId) {
        Optional<BorrowDBO> obj = iBorrowRepositoryAdapter.findById(borrowId);
        return obj.map(BorrowDBO::toDomain).orElse(null);
    }
}
