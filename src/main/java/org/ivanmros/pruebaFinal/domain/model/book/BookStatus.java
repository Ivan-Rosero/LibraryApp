package org.ivanmros.pruebaFinal.domain.model.book;

import org.ivanmros.pruebaFinal.domain.usecase.utils.Constants;

public class BookStatus {

    private String value;

    public BookStatus(String value){
        if(value != null){
            this.value = value;
        }else{
            value = Constants.BOOK_AVAILABLE;
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
