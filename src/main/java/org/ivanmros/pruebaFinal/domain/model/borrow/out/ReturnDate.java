package org.ivanmros.pruebaFinal.domain.model.borrow.out;

import java.time.LocalDate;

public class ReturnDate {

    private LocalDate value;

    public ReturnDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    public void setValue(LocalDate value) {
        this.value = value;
    }
}
