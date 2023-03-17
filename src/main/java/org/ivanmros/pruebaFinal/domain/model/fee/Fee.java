package org.ivanmros.pruebaFinal.domain.model.fee;

import org.ivanmros.pruebaFinal.domain.model.borrow.in.StartDate;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowId;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.EndDate;
import org.ivanmros.pruebaFinal.domain.model.user.UserId;
import org.ivanmros.pruebaFinal.domain.model.user.UserName;

public class Fee {

    private final FeeId feeId;
    private final UserId userId;
    private final UserName userName;
    private final BorrowId borrowId;
    private final StartDate startDate;
    private final EndDate endDate;
    private final FeeAmount feeAmount;

    public Fee(FeeId feeId, UserId userId, UserName userName, BorrowId borrowId, StartDate startDate, EndDate endDate, FeeAmount feeAmount) {
        this.feeId = feeId;
        this.userId = userId;
        this.userName = userName;
        this.borrowId = borrowId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.feeAmount = feeAmount;
    }

    public FeeId getFeeId() {
        return feeId;
    }

    public UserId getUserId() {
        return userId;
    }

    public UserName getUserName() {
        return userName;
    }

    public BorrowId getBorrowId() {
        return borrowId;
    }

    public StartDate getStartDate() {
        return startDate;
    }

    public EndDate getEndDate() {
        return endDate;
    }

    public FeeAmount getFeeAmount() {
        return feeAmount;
    }
}
