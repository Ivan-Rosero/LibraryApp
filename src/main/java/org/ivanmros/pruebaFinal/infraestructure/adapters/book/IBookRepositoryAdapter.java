package org.ivanmros.pruebaFinal.infraestructure.adapters.book;

import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.BookDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepositoryAdapter extends JpaRepository<BookDBO, Integer> {
}
