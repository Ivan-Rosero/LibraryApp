package org.ivanmros.pruebaFinal.domain.usecase.utils;

import java.time.LocalDate;

public class Functions {

    public static Boolean rightDate(LocalDate startDate, LocalDate endDate){
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("La fecha a ingresar no debe ser anterior a " + startDate);
        }else {
            return true;
        }
    }

    public static LocalDate endDateFunction(LocalDate startDate){
        LocalDate endDate = startDate.plusDays(7);
        return endDate;
    }

    public static LocalDate defaultDateFunction(){
        LocalDate returnDate = LocalDate.now();
        return returnDate;
    }

    public static Boolean penaltyFee(LocalDate endDate, LocalDate returnDate){
        Boolean fee;
        if(returnDate.isBefore(endDate)){
            fee = false;
            return fee;
        }else{
            fee = true;
            return fee;
        }
    }
}
