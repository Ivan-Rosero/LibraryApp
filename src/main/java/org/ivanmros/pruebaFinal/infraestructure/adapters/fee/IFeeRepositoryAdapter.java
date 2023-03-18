package org.ivanmros.pruebaFinal.infraestructure.adapters.fee;

import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.FeeDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFeeRepositoryAdapter extends JpaRepository<FeeDBO, Integer> {

    List<FeeDBO> findFeeByUserId(String userId);


}
