package org.ivanmros.pruebaFinal.domain.model.gateway;

import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowOut;

import java.util.List;

public interface IBorrowRepository {

    BorrowOut createBorrow(BorrowOut borrowOut);

    List<BorrowOut> findByUserId(String userId);

    List<BorrowOut> findAllBorrows();

    BorrowOut updateBorrow(BorrowOut borrowOut);

    BorrowOut findByBookId(Integer bookId);

    BorrowOut findById(Integer borrowId);
}
