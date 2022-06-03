package com.mpp.librarysys.javafx.helper;

import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.BookCopy;
import javafx.util.StringConverter;
import org.springframework.stereotype.Component;

@Component
public class AppGeneralObjectConverter {

    public StringConverter<Book> getBookObjStringConverter() {
        return new StringConverter<Book>() {

            @Override
            public String toString(Book book) {
                return book.getId() + ": " + book.getTitle() + ": " + book.getISBNNumber();
            }

            @Override
            public Book fromString(String s) {
                return null;
            }
        };
    }

    public StringConverter<BookCopy> getBookCopyObjStringConverter() {
        return new StringConverter<BookCopy>() {

            @Override
            public String toString(BookCopy bookCopy) {
                return bookCopy.getBook().getTitle() + ": " + bookCopy.getCopyNumber();
            }

            @Override
            public BookCopy fromString(String s) {
                return null;
            }
        };
    }

}
