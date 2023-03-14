package org.ivanmros.pruebaFinal.domain.model.book;

public class BookStatus {

    private Boolean value;

    public BookStatus(Boolean value){
        if(value != null){
            this.value = value;
        }else{
            value = true;
            this.value = value;
        }
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
