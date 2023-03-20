package org.ivanmros.pruebaFinal.domain.model.book.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ivanmros.pruebaFinal.domain.model.book.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

    private Integer idBook;
    @NotNull
    @NotEmpty(message = "Escriba nombre del libro")
    @Pattern(regexp = "[\\p{L}\\s]+", message = "Ingrese nombre de libro con caracteres válidos")
    private String bookName;
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
