package org.ivanmros.pruebaFinal.domain.model.borrow.out;

import org.ivanmros.pruebaFinal.domain.usecase.utils.Constants;

public class BorrowStatus {

    private String value;

    public BorrowStatus(String value) {
        if(value != null){
            this.value = value;
        }else{
            value = Constants.BORROW_CREATED;
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
