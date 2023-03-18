package org.ivanmros.pruebaFinal.domain.model.gateway;

import org.ivanmros.pruebaFinal.domain.model.fee.Fee;

import java.util.List;

public interface IFeeRepository {

    Fee createFee(Fee fee);

    Fee updateFee(Fee fee);

    List<Fee> findAllFees();

    List<Fee> findFeeByUserId(String userId);

}
