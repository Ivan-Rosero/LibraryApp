package org.ivanmros.pruebaFinal.domain.model.borrow.in;

import java.time.LocalDate;

public class StartDate {

    private final LocalDate value;

    public StartDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
