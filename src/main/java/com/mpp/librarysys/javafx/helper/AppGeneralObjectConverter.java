package com.mpp.librarysys.javafx.helper;

import com.mpp.librarysys.lms.entities.Author;
import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.BookCopy;
import javafx.util.StringConverter;
import org.springframework.stereotype.Component;

public class AppGeneralObjectConverter {

    public static StringConverter<Book> getBookObjStringConverter() {
        return new StringConverter<Book>() {

            @Override
            public String toString(Book book) {
                if(null == book) return "Select item";
                return book.getId() + ": " + book.getTitle() + ": " + book.getISBNNumber();
            }

            @Override
            public Book fromString(String s) {
                return null;
            }
        };
    }

    public static StringConverter<Author> getAuthorObjStringConverter() {
        return new StringConverter<Author>() {

            @Override
            public String toString(Author author) {
                if(null == author) return "Select item";
                return author.getId() + ": " + author.getAuthorName();
            }

            @Override
            public Author fromString(String s) {
                return null;
            }
        };
    }

    public static StringConverter<BookCopy> getBookCopyObjStringConverter() {
        return new StringConverter<BookCopy>() {

            @Override
            public String toString(BookCopy bookCopy) {
                if(null == bookCopy) return "Select item";
                return bookCopy.getBook().getTitle() + ": " + bookCopy.getCopyNumber();
            }

            @Override
            public BookCopy fromString(String s) {
                return null;
            }
        };
    }

}
