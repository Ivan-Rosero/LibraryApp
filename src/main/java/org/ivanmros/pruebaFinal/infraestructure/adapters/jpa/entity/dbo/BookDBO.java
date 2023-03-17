package org.ivanmros.pruebaFinal.infraestructure.adapters.jpa.entity.dbo;

import jakarta.persistence.*;
import lombok.*;
import org.ivanmros.pruebaFinal.domain.model.book.Book;
import org.ivanmros.pruebaFinal.domain.model.book.BookId;
import org.ivanmros.pruebaFinal.domain.model.book.BookName;
import org.ivanmros.pruebaFinal.domain.model.book.BookStatus;

@Entity
@Table(name="book")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BOOK")
    private Integer idBook;

    @Column(name = "BOOK_NAME")
    private String bookName;
    @Column(name = "BOOK_STATUS")
    private Boolean bookStatus;

    public static Book toDomain(BookDBO bookDBO){
        return new Book(
                new BookId(bookDBO.getIdBook()),
                new BookName(bookDBO.getBookName()),
                new BookStatus(bookDBO.getBookStatus())
        );
    }

    public static BookDBO fromDomain(Book book){
        return new BookDBO(
                book.getIdBook().getValue(),
                book.getBookName().getValue(),
                book.getBookStatus().getValue()
        );
    }
}
