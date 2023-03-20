package org.ivanmros.pruebaFinal.domain.usecase.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Functions {

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

    public static Double daysFee(LocalDate endDate, LocalDate returnDate){
        Long daysLate = ChronoUnit.DAYS.between(endDate, returnDate);

        Double totalFee = daysLate * Constants.FEE_PER_DAY;

        return totalFee;
    }

    public static Boolean theDateIsRight(LocalDate borrowDate, LocalDate today){
        if (borrowDate.isAfter(today)) {
            return true;
        }else {
            return false;
        }
    }
}
