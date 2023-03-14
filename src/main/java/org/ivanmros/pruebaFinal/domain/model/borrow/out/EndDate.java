package org.ivanmros.pruebaFinal.domain.model.borrow.out;

import java.time.LocalDate;

public class EndDate {

    private final LocalDate value;

    public EndDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
