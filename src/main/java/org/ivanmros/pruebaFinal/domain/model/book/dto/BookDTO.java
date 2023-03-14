package org.ivanmros.pruebaFinal.domain.model.book.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ivanmros.pruebaFinal.domain.model.book.Book;
import org.ivanmros.pruebaFinal.domain.model.book.BookId;
import org.ivanmros.pruebaFinal.domain.model.book.BookName;
import org.ivanmros.pruebaFinal.domain.model.book.BookStatus;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

    //@NotNull
    private Integer idBook;
    @NotNull
    @NotEmpty(message = "Escriba nombre del libro")
    private String bookName;

    @NotNull
    private boolean bookStatus;

    public static Book toDomain(BookDTO bookDTO){
        return new Book(
                new BookId(bookDTO.getIdBook()),
                new BookName(bookDTO.getBookName()),
                new BookStatus(bookDTO.isBookStatus())
        );
    }

    public static BookDTO fromDomain(Book book){
        return new BookDTO(book.getIdBook().getValue(),
                book.getBookName().getValue(),
                book.getBookStatus().getValue());
    }

}
