package org.ivanmros.pruebaFinal.domain.usecase.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Functions {

    public static LocalDate endDateFunction(LocalDate startDate){
        LocalDate endDate = startDate.plusDays(Constants.BORROW_DAYS);
        return endDate;
    }

    public static LocalDate defaultDateFunction(){
        LocalDate returnDate = LocalDate.now();
        return returnDate;
    }

    public static String penaltyFee(LocalDate endDate, LocalDate returnDate){
        String fee;
        if(returnDate.isBefore(endDate)){
            fee = Constants.FEE_DEFAULT;
            return fee;
        }else{
            fee = Constants.FEE_PENALTY;
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
