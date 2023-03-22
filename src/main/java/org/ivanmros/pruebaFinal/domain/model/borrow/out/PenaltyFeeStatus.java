package org.ivanmros.pruebaFinal.domain.model.borrow.out;

import org.ivanmros.pruebaFinal.domain.usecase.utils.Constants;

public class PenaltyFeeStatus {

    private String value;

    public PenaltyFeeStatus(String value) {
        if(value != null){
            this.value = value;
        }else{
            value = Constants.FEE_DEFAULT;
            this.value = value;
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
