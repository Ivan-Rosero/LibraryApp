package org.ivanmros.pruebaFinal.domain.usecase;

import org.ivanmros.pruebaFinal.domain.model.fee.Fee;
import org.ivanmros.pruebaFinal.domain.model.fee.dto.FeeDTO;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBorrowRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IFeeRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeeUseCase {
    private final IFeeRepository iFeeRepository;

    public FeeUseCase(IFeeRepository iFeeRepository) {
        this.iFeeRepository = iFeeRepository;
    }

    public ArrayList<FeeDTO> findAllFees() {
        List<Fee> feeList = this.iFeeRepository.findAllFees();
        return (ArrayList<FeeDTO>) feeList.stream().map(FeeDTO::fromDomain).collect(Collectors.toList());

    }

    public ArrayList<FeeDTO> findFeeByUserId(String userId){
        List<Fee> userFees = this.iFeeRepository.findFeeByUserId(userId);
        return (ArrayList<FeeDTO>) userFees.stream().map(FeeDTO::fromDomain).collect(Collectors.toList());
    }
}
