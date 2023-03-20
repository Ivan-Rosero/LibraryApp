package org.ivanmros.pruebaFinal.infraestructure.adapters.fee;

import lombok.AllArgsConstructor;
import org.ivanmros.pruebaFinal.domain.model.fee.Fee;
import org.ivanmros.pruebaFinal.domain.model.gateway.IFeeRepository;
import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.FeeDBO;
import org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo.UserDBO;
import org.ivanmros.pruebaFinal.infraestructure.adapters.user.IUserRepositoryAdapter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeeRepositoryAdapter implements IFeeRepository {

    private final IFeeRepositoryAdapter iFeeRepositoryAdapter;
    private final IUserRepositoryAdapter iUserRepositoryAdapter;

    @Override
    public Fee createFee(Fee fee) throws IllegalArgumentException{
        return iFeeRepositoryAdapter.save(new FeeDBO().fromDomain(fee)).toDomain();
    }

    @Override
    public List<Fee> findAllFees() {
        return iFeeRepositoryAdapter.findAll().stream().map(FeeDBO::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Fee> findFeeByUserId(String userId) {
        List<FeeDBO> feesOfUserList = iFeeRepositoryAdapter.findFeeByUserId(userId);
        Optional<UserDBO> userFound = iUserRepositoryAdapter.findById(userId);

        if(userFound.isEmpty()){
            throw new NullPointerException("No existe el usuario con el id: " + userId);
        }else{
            if(feesOfUserList.isEmpty()){
                throw new NullPointerException("El usuario con id: " + userId + " no tiene multas actualmente.");
            }
            return feesOfUserList.stream().map(FeeDBO::toDomain).collect(Collectors.toList());
        }
    }
}
