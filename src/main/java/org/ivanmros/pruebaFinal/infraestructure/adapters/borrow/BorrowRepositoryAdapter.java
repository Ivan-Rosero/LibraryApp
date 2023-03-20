package org.ivanmros.pruebaFinal.infraestructure.adapters.borrow;

import lombok.AllArgsConstructor;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowOut;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBorrowRepository;
import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.BorrowDBO;
import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.FeeDBO;
import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.UserDBO;
import org.ivanmros.pruebaFinal.infraestructure.adapters.user.IUserRepositoryAdapter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BorrowRepositoryAdapter implements IBorrowRepository {

    private final IBorrowRepositoryAdapter iBorrowRepositoryAdapter;
    private final IUserRepositoryAdapter iUserRepositoryAdapter;

    @Override
    public BorrowOut createBorrow(BorrowOut borrowOut) {
        return iBorrowRepositoryAdapter.save(new BorrowDBO().fromDomain(borrowOut)).toDomain();
    }

    @Override
    public List<BorrowOut> findByUserId(String userId) {
        List<BorrowDBO> borrowedByUserList = iBorrowRepositoryAdapter.findByUserId(userId);
        Optional<UserDBO> userFound = iUserRepositoryAdapter.findById(userId);

        if(userFound.isEmpty()){
            throw new NullPointerException("No existe el usuario con el id ingresado");
        }else{
            if(borrowedByUserList.isEmpty()){
                throw new NullPointerException("El usuario con id: " + userId + " no ha solicitado prestamos aún.");
            }
            return borrowedByUserList.stream().map(BorrowDBO::toDomain).collect(Collectors.toList());
        }

//        if(borrowedByUserList.isEmpty()){
//            throw new NullPointerException("El usuario con id " + userId + " no ha solicitado prestamos aún.");
//        }
//        return borrowedByUserList.stream().map(BorrowDBO::toDomain).collect(Collectors.toList());
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
        Optional<BorrowDBO> borrowIdFound = iBorrowRepositoryAdapter.findById(borrowOut.getBorrowId().getValue());
        if(!borrowIdFound.isPresent()){
            throw new NullPointerException("No hay registros de prestamos con el id " + borrowIdFound);
        }else{
            return iBorrowRepositoryAdapter.save(new BorrowDBO().fromDomain(borrowOut)).toDomain();
        }
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
