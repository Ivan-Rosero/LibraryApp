package org.ivanmros.pruebaFinal.infraestructure.adapters.user;

import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.UserDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepositoryAdapter extends JpaRepository<UserDBO, Integer> {

}
