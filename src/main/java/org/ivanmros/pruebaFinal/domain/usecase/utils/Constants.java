package org.ivanmros.pruebaFinal.domain.usecase.utils;

import java.time.format.DateTimeFormatter;

public class Constants {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final double FEE_PER_DAY = 1000.0;

    public static final int BORROW_DAYS = 7;

    public static final String BOOK_AVAILABLE = "Disponible";
    public static final String BOOK_NOT_AVAILABLE = "Prestado";

    public static final String BORROW_CREATED = "Vigente";

    public static final String BORROW_RETURNED = "Devuelto";

    public static final String BORROW_FEE = "Vencido";

    public static final String FEE_PENALTY = "Tiene multa";
    public static final String FEE_DEFAULT = "Sin Multas";

}
