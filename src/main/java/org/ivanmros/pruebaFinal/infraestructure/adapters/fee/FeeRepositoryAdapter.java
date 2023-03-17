package org.ivanmros.pruebaFinal.infraestructure.adapters.fee;

import lombok.AllArgsConstructor;
import org.ivanmros.pruebaFinal.domain.model.fee.Fee;
import org.ivanmros.pruebaFinal.domain.model.gateway.IFeeRepository;
import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.FeeDBO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeeRepositoryAdapter implements IFeeRepository {

    private final IFeeRepositoryAdapter iFeeRepositoryAdapter;

    @Override
    public Fee createFee(Fee fee) throws IllegalArgumentException{
        return iFeeRepositoryAdapter.save(new FeeDBO().fromDomain(fee)).toDomain();
    }

    @Override
    public Fee updateFee(Fee fee) {
        return null;
    }

    @Override
    public List<Fee> findAllFees() {
        return iFeeRepositoryAdapter.findAll().stream().map(FeeDBO::toDomain).collect(Collectors.toList());
    }

    @Override
    public Fee findFeeByUserId(Integer userId) {
        return null;
    }
}
