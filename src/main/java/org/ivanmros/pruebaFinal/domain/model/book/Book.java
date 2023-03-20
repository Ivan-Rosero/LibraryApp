package org.ivanmros.pruebaFinal.domain.model.book;


public class Book{

    private final BookId idBook;
    private final BookName bookName;
    private BookStatus bookStatus;

    public Book(BookId idBook, BookName bookName, BookStatus bookStatus) {
        this.idBook = idBook;
        this.bookName = bookName;
        this.bookStatus = bookStatus;
    }

    public BookId getIdBook() {
        return idBook;
    }

    public BookName getBookName() {
        return bookName;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

}
