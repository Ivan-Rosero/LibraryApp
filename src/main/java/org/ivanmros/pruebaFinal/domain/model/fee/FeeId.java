package org.ivanmros.pruebaFinal.domain.model.fee;

import static org.springframework.util.Assert.notNull;

public class FeeId {

    private final Integer value;

    public FeeId(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
