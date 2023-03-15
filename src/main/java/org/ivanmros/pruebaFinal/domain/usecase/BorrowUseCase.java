package org.ivanmros.pruebaFinal.domain.usecase;

import org.ivanmros.pruebaFinal.domain.model.book.Book;
import org.ivanmros.pruebaFinal.domain.model.book.BookStatus;
import org.ivanmros.pruebaFinal.domain.model.borrow.dto.BorrowOutDTO;
import org.ivanmros.pruebaFinal.domain.model.borrow.in.StartDate;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowId;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowOut;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.BorrowStatus;
import org.ivanmros.pruebaFinal.domain.model.borrow.out.EndDate;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBookRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IBorrowRepository;
import org.ivanmros.pruebaFinal.domain.model.gateway.IUserRepository;
import org.ivanmros.pruebaFinal.domain.model.user.User;
import org.ivanmros.pruebaFinal.domain.usecase.utils.Constants;
import org.ivanmros.pruebaFinal.domain.usecase.utils.Functions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowUseCase {

    private final IBookRepository iBookRepository;
    private final IUserRepository iUserRepository;
    private final IBorrowRepository iBorrowRepository;

    public BorrowUseCase(IBookRepository iBookRepository, IUserRepository iUserRepository,
                         IBorrowRepository iBorrowRepository) {
        this.iBookRepository = iBookRepository;
        this.iUserRepository = iUserRepository;
        this.iBorrowRepository = iBorrowRepository;
    }

    public BorrowOutDTO createBorrow(BorrowOutDTO borrowOutDTO){
        User user = iUserRepository.findUserById(borrowOutDTO.getUserId());
        Book book = iBookRepository.findBookById(borrowOutDTO.getBookId());

        LocalDate startDate = (LocalDate.parse(borrowOutDTO.getStartDate(), Constants.FORMATTER));
        Boolean bookAvailable = book.getBookStatus().getValue();

        LocalDate endDate = Functions.endDateFunction(startDate);
        //LocalDate endDate1 = (LocalDate.parse(borrowOutDTO.getEndDate(), Constants.FORMATTER));

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
                    new BookStatus(false),
                    new StartDate(startDate),
                    new EndDate(endDate),
                    new BorrowStatus(true)
            );

            iBookRepository.updateBook(book1);
            return BorrowOutDTO.fromDomain(this.iBorrowRepository.createBorrow(borrowOut));
        }else{
            throw new IllegalArgumentException("Este libro no está disponible.");
        }
    }

    public ArrayList<BorrowOutDTO> findByUserId(Integer userId){
        List<BorrowOut> borrowedBooksByUser = this.iBorrowRepository.findByUserId(userId);
        return (ArrayList<BorrowOutDTO>) borrowedBooksByUser.stream().map(BorrowOutDTO::fromDomain).collect(Collectors.toList());
    }

    public ArrayList<BorrowOutDTO> findAllBorrows(){
        List<BorrowOut> totalBorrowedBooksList = this.iBorrowRepository.findAllBorrows();
        return (ArrayList<BorrowOutDTO>) totalBorrowedBooksList.stream().map(BorrowOutDTO::fromDomain).collect(Collectors.toList());
    }
}
