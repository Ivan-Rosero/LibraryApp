package org.ivanmros.pruebaFinal.domain.model.gateway;

import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowOut;

import java.util.List;

public interface IBorrowRepository {

    BorrowOut createBorrow(BorrowOut borrowOut);

    List<BorrowOut> findByUserId(Integer userId);

    List<BorrowOut> findAllBorrows();
}