package org.ivanmros.pruebaFinal.domain.usecase;

import jakarta.persistence.EntityExistsException;
import org.ivanmros.pruebaFinal.domain.model.fee.Fee;
import org.ivanmros.pruebaFinal.domain.model.fee.dto.FeeDTO;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBorrowRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IFeeRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IUserRepository;
import org.ivanmros.pruebaFinal.domain.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FeeUseCase {

    private final IBorrowRepository iBorrowRepository;
    private final IFeeRepository iFeeRepository;
    private final IUserRepository iUserRepository;

    public FeeUseCase(IBorrowRepository iBorrowRepository, IFeeRepository iFeeRepository, IUserRepository iUserRepository) {
        this.iBorrowRepository = iBorrowRepository;
        this.iFeeRepository = iFeeRepository;
        this.iUserRepository = iUserRepository;
    }

    public ArrayList<FeeDTO> findAllFees() {
        List<Fee> feeList = this.iFeeRepository.findAllFees();
        return (ArrayList<FeeDTO>) feeList.stream().map(FeeDTO::fromDomain).collect(Collectors.toList());

    }

    public ArrayList<FeeDTO> findFeeByUserId(String userId){
        List<Fee> userFees = this.iFeeRepository.findFeeByUserId(userId);
        return (ArrayList<FeeDTO>) userFees.stream().map(FeeDTO::fromDomain).collect(Collectors.toList());
    }

//    public ArrayList<Fee> findFeeByUserId(Integer userId){
//        Optional<User> userFound = Optional.ofNullable(iUserRepository.findUserById(userId));
//        Optional<List<Fee>> feeFound = Optional.ofNullable(iFeeRepository.findFeeByUserId(userId));
//        if (userFound.isPresent()){
//            if (feeFound.isPresent()){
//                return iFeeRepository.findFeeByUserId(userId);
//            }else{
//                throw new EntityExistsException("El usuario no tiene multas.");
//            }
//        }else {
//            throw new EntityExistsException("El usuario ingresado no existe.");
//        }
//    }


}
