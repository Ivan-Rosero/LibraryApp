package org.ivanmros.pruebaFinal.domain.model.fee;

public class FeeAmount {

    private Double value;

    public FeeAmount(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
