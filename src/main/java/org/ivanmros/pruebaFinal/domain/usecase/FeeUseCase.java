package org.ivanmros.pruebaFinal.domain.usecase;

import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowOut;
import org.ivanmros.pruebaFinal.domain.model.fee.Fee;
import org.ivanmros.pruebaFinal.domain.model.fee.FeeAmount;
import org.ivanmros.pruebaFinal.domain.model.fee.dto.FeeDTO;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBorrowRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IFeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeeUseCase {

    private final IBorrowRepository iBorrowRepository;
    private final IFeeRepository iFeeRepository;

    public FeeUseCase(IBorrowRepository iBorrowRepository, IFeeRepository iFeeRepository) {
        this.iBorrowRepository = iBorrowRepository;
        this.iFeeRepository = iFeeRepository;
    }

    public ArrayList<FeeDTO> findAllFees() {
        List<Fee> feeList = this.iFeeRepository.findAllFees();
        return (ArrayList<FeeDTO>) feeList.stream().map(FeeDTO::fromDomain).collect(Collectors.toList());
    }
}
