package org.ivanmros.pruebaFinal.domain.usecase;

import org.ivanmros.pruebaFinal.domain.model.book.Book;
import org.ivanmros.pruebaFinal.domain.model.book.BookStatus;
import org.ivanmros.pruebaFinal.domain.model.borrow.dto.BorrowOutDTO;
import org.ivanmros.pruebaFinal.domain.model.borrow.in.StartDate;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.*;
import org.ivanmros.pruebaFinal.domain.model.fee.Fee;
import org.ivanmros.pruebaFinal.domain.model.fee.FeeAmount;
import org.ivanmros.pruebaFinal.domain.model.fee.FeeId;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBookRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBorrowRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IFeeRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IUserRepository;
import org.ivanmros.pruebaFinal.domain.model.user.User;
import org.ivanmros.pruebaFinal.domain.usecase.utils.Constants;
import org.ivanmros.pruebaFinal.domain.usecase.utils.Functions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BorrowUseCase {

    private final IBookRepository iBookRepository;
    private final IUserRepository iUserRepository;
    private final IBorrowRepository iBorrowRepository;
    private final IFeeRepository iFeeRepository;

    public BorrowUseCase(IBookRepository iBookRepository, IUserRepository iUserRepository, IBorrowRepository iBorrowRepository, IFeeRepository iFeeRepository) {
        this.iBookRepository = iBookRepository;
        this.iUserRepository = iUserRepository;
        this.iBorrowRepository = iBorrowRepository;
        this.iFeeRepository = iFeeRepository;
    }

    public BorrowOutDTO createBorrow(BorrowOutDTO borrowOutDTO) {
        User user = iUserRepository.findUserById(borrowOutDTO.getUserId());
        Book book = iBookRepository.findBookById(borrowOutDTO.getBookId());

        LocalDate startDate = (LocalDate.parse(borrowOutDTO.getStartDate(), Constants.FORMATTER));
        String bookAvailable = book.getBookStatus().getValue();

        LocalDate defaultDate = (LocalDate.parse("01/01/2020", Constants.FORMATTER));
        LocalDate endDate = Functions.endDateFunction(startDate);
        LocalDate today = Functions.defaultDateFunction();

        Boolean dateIsRight = Functions.theDateIsRight(startDate, today);

        if (dateIsRight == false) {
            if (bookAvailable.equalsIgnoreCase(Constants.BOOK_AVAILABLE)) {
                Book book1 = new Book(
                        book.getIdBook(),
                        book.getBookName(),
                        new BookStatus(Constants.BOOK_NOT_AVAILABLE)
                );

                BorrowOut borrowOut = new BorrowOut(
                        new BorrowId(null),
                        user.getIdUser(),
                        user.getUserName(),
                        book.getIdBook(),
                        book.getBookName(),
                        book1.getBookStatus(),
                        new StartDate(startDate),
                        new EndDate(endDate),
                        new ReturnDate(defaultDate),
                        new BorrowStatus(Constants.BORROW_CREATED),
                        new PenaltyFeeStatus(Constants.FEE_DEFAULT)
                );

                iBookRepository.updateBook(book1);
                return BorrowOutDTO.fromDomain(this.iBorrowRepository.createBorrow(borrowOut));
            } else {
                throw new IllegalArgumentException("Este libro no está disponible.");
            }
        } else {
            throw new IllegalArgumentException("La fecha de inicio del prestamo no puede ser posterior a " + today);
        }

    }


    public BorrowOut updateBorrow(Integer borrowId) throws Exception {
        Optional<BorrowOut> borrow = Optional.ofNullable(iBorrowRepository.findById(borrowId));
        Book book = iBookRepository.findBookById(borrow.get().getBookId().getValue());

        String borrowedBook = book.getBookStatus().getValue();
        LocalDate today = Functions.defaultDateFunction();
        String penaltyFee = Functions.penaltyFee(borrow.get().getEndDate().getValue(), today);

        if (!borrow.isPresent()) {
            throw new Exception("El prestamo del libro: " + book.getBookName().getValue() + " no se ha realizado. Aún está en biblioteca");
        } else {
            if (borrowedBook == Constants.BOOK_AVAILABLE) {
                throw new NullPointerException("El libro correspondiente a este prestamo ya fue devuelto.");
            }
            Book book1 = new Book(
                    book.getIdBook(),
                    book.getBookName(),
                    new BookStatus(Constants.BOOK_AVAILABLE)
            );

            iBookRepository.updateBook(book1);

            if (penaltyFee.equalsIgnoreCase(Constants.FEE_PENALTY)) {
                LocalDate endDate = borrow.get().getEndDate().getValue();
                Double totalPenaltyFee = Functions.daysFee(endDate, today);

                Fee fee = new Fee(
                        new FeeId(null),
                        borrow.get().getUserId(),
                        borrow.get().getUserName(),
                        borrow.get().getBorrowId(),
                        borrow.get().getStartDate(),
                        borrow.get().getEndDate(),
                        new FeeAmount(totalPenaltyFee)
                );
                iFeeRepository.createFee(fee);
                return iBorrowRepository.updateBorrow(new BorrowOut(
                        borrow.get().getBorrowId(),
                        borrow.get().getUserId(),
                        borrow.get().getUserName(),
                        borrow.get().getBookId(),
                        borrow.get().getBookName(),
                        book1.getBookStatus(),
                        borrow.get().getStartDate(),
                        borrow.get().getEndDate(),
                        new ReturnDate(today),
                        new BorrowStatus(Constants.BORROW_FEE),
                        new PenaltyFeeStatus(penaltyFee)));
            } else {

                return iBorrowRepository.updateBorrow(new BorrowOut(
                        borrow.get().getBorrowId(),
                        borrow.get().getUserId(),
                        borrow.get().getUserName(),
                        borrow.get().getBookId(),
                        borrow.get().getBookName(),
                        book1.getBookStatus(),
                        borrow.get().getStartDate(),
                        borrow.get().getEndDate(),
                        new ReturnDate(today),
                        new BorrowStatus(Constants.BORROW_RETURNED),
                        new PenaltyFeeStatus(penaltyFee)
                ));
            }
        }
    }

    public ArrayList<BorrowOutDTO> findByUserId(String userId) {
        List<BorrowOut> borrowedBooksByUser = this.iBorrowRepository.findByUserId(userId);
        return (ArrayList<BorrowOutDTO>) borrowedBooksByUser.stream().map(BorrowOutDTO::fromDomain).collect(Collectors.toList());
    }


    public ArrayList<BorrowOutDTO> findAllBorrows() {
        List<BorrowOut> totalBorrowedBooksList = this.iBorrowRepository.findAllBorrows();
        return (ArrayList<BorrowOutDTO>) totalBorrowedBooksList.stream().map(BorrowOutDTO::fromDomain).collect(Collectors.toList());
    }

    public ArrayList<BorrowOutDTO> findPendingBorrows() {
        List<BorrowOut> totalBorrowedBooksList = this.iBorrowRepository.findAllBorrows();
        List<BorrowOut> pendingBorrows = totalBorrowedBooksList.stream()
                .filter(pb -> pb.getBorrowStatus().getValue().equalsIgnoreCase(Constants.BORROW_CREATED))
                .collect(Collectors.toList());

        return (ArrayList<BorrowOutDTO>) pendingBorrows.stream().map(BorrowOutDTO::fromDomain).collect(Collectors.toList());
    }

    public ArrayList<BorrowOutDTO> findExpiredBorrows() {
        List<BorrowOut> totalBorrowedBooksList = this.iBorrowRepository.findAllBorrows();
        List<BorrowOut> pendingBorrows = totalBorrowedBooksList.stream()
                .filter(pb -> pb.getBorrowStatus().getValue().equalsIgnoreCase(Constants.BORROW_FEE))
                .collect(Collectors.toList());

        return (ArrayList<BorrowOutDTO>) pendingBorrows.stream().map(BorrowOutDTO::fromDomain).collect(Collectors.toList());
    }
}
