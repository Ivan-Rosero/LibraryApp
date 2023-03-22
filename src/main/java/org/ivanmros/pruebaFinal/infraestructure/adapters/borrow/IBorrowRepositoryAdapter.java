package org.ivanmros.pruebaFinal.infraestructure.adapters.borrow;

import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.BorrowDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBorrowRepositoryAdapter extends JpaRepository<BorrowDBO, Integer> {
    List<BorrowDBO> findByUserId(String userId);
}
