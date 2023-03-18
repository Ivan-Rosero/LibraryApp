package org.ivanmros.pruebaFinal.domain.usecase;

import jakarta.persistence.EntityExistsException;
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

    public BorrowOutDTO createBorrow(BorrowOutDTO borrowOutDTO){
        User user = iUserRepository.findUserById(borrowOutDTO.getUserId());
        Book book = iBookRepository.findBookById(borrowOutDTO.getBookId());

        LocalDate startDate = (LocalDate.parse(borrowOutDTO.getStartDate(), Constants.FORMATTER));
        Boolean bookAvailable = book.getBookStatus().getValue();

        LocalDate defaultDate = (LocalDate.parse("01/01/2020", Constants.FORMATTER));
        LocalDate endDate = Functions.endDateFunction(startDate);
        LocalDate today = Functions.defaultDateFunction();

        Boolean dateIsRight = Functions.theDateIsRight(startDate, today);

        if(dateIsRight == false){
            if(bookAvailable == true) {
                Book book1 = new Book(
                        book.getIdBook(),
                        book.getBookName(),
                        new BookStatus(false)
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
                        new BorrowStatus(true),
                        new PenaltyFeeBoolean(false)
                );

                iBookRepository.updateBook(book1);
                return BorrowOutDTO.fromDomain(this.iBorrowRepository.createBorrow(borrowOut));
            }else{
                throw new IllegalArgumentException("Este libro no está disponible.");
            }
        } else {
            throw new IllegalArgumentException("La fecha de inicio del prestamo no puede ser posterior a " + today);
        }

    }

//    public BorrowOut updateBorrow(Integer borrowId){
//        Optional<BorrowOut> borrow = Optional.ofNullable(iBorrowRepository.findById(borrowId));
//        Book book = iBookRepository.findBookById(borrow.get().getBookId().getValue());
//
//        Boolean borrowedBook = book.getBookStatus().getValue();
//        LocalDate today = Functions.defaultDateFunction();
//        Boolean penaltyFee = Functions.penaltyFee(borrow.get().getEndDate().getValue(), today);
//
//        if(borrow.isPresent()){
//            if(borrowedBook == true) {
//                throw new IllegalArgumentException("No se puede actualizar este prestamo.");
//            }
//            Book book1 = new Book(
//                    book.getIdBook(),
//                    book.getBookName(),
//                    new BookStatus(true)
//            );
//
//            iBookRepository.updateBook(book1);
//            return iBorrowRepository.updateBorrow(new BorrowOut(
//                    borrow.get().getBorrowId(),
//                    borrow.get().getUserId(),
//                    borrow.get().getUserName(),
//                    borrow.get().getBookId(),
//                    borrow.get().getBookName(),
//                    book1.getBookStatus(),
//                    borrow.get().getStartDate(),
//                    borrow.get().getEndDate(),
//                    new ReturnDate(today),
//                    new BorrowStatus(false),
//                    new PenaltyFeeBoolean(penaltyFee)
//            ));
//
//        }
//        throw new EntityExistsException("El prestamo del libro: " + book.getBookName().getValue() + " este libro no se ha realizado. Aún está en biblioteca");
//    }

    public BorrowOut updateBorrow(Integer borrowId){
        Optional<BorrowOut> borrow = Optional.ofNullable(iBorrowRepository.findById(borrowId));
        Book book = iBookRepository.findBookById(borrow.get().getBookId().getValue());

        Boolean borrowedBook = book.getBookStatus().getValue();
        LocalDate today = Functions.defaultDateFunction();
        Boolean penaltyFee = Functions.penaltyFee(borrow.get().getEndDate().getValue(), today);

        if(borrow.isPresent()){
            if(borrowedBook == true) {
                throw new NullPointerException("El libro de este prestamo ya fue devuelto.");
            }
            Book book1 = new Book(
                    book.getIdBook(),
                    book.getBookName(),
                    new BookStatus(true)
            );

            iBookRepository.updateBook(book1);

            if(penaltyFee == true){
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
            }

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
                    new BorrowStatus(false),
                    new PenaltyFeeBoolean(penaltyFee)
            ));

        }
        throw new EntityExistsException("El prestamo del libro: " + book.getBookName().getValue() + " este libro no se ha realizado. Aún está en biblioteca");
    }

    public ArrayList<BorrowOutDTO> findByUserId(String userId){
        List<BorrowOut> borrowedBooksByUser = this.iBorrowRepository.findByUserId(userId);
        return (ArrayList<BorrowOutDTO>) borrowedBooksByUser.stream().map(BorrowOutDTO::fromDomain).collect(Collectors.toList());
    }

    public ArrayList<BorrowOutDTO> findAllBorrows(){
        List<BorrowOut> totalBorrowedBooksList = this.iBorrowRepository.findAllBorrows();
        return (ArrayList<BorrowOutDTO>) totalBorrowedBooksList.stream().map(BorrowOutDTO::fromDomain).collect(Collectors.toList());
    }

    public BorrowOutDTO findByBookId(Integer bookId){
        BorrowOut borrowOut = this.iBorrowRepository.findByBookId(bookId);
        return BorrowOutDTO.fromDomain(borrowOut);
    }


}
