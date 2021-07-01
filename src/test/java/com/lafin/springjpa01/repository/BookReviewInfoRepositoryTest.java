package com.lafin.springjpa01.repository;

import com.lafin.springjpa01.domain.Book;
import com.lafin.springjpa01.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    void crud() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(new Book());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(bookReviewInfoRepository.findAll());
    }

    @Test
    void crud2() {
        givenBookReviewInfo();

        Book result = bookReviewInfoRepository.findById(1L)
                                            .orElseThrow(RuntimeException::new)
                                            .getBook();

        System.out.println(result);
    }

    private Book givenBook() {
        Book book = new Book();
        book.setName("JPA 초격자 패키지");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);
        System.out.println(bookReviewInfoRepository.findAll());
    }
}