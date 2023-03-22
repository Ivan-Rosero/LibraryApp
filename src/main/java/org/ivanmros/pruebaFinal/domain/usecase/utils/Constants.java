package org.ivanmros.pruebaFinal.domain.usecase.utils;

import java.time.format.DateTimeFormatter;

public class Constants {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final double FEE_PER_DAY = 1000.0;

    public static final int BORROW_DAYS = 7;
}
