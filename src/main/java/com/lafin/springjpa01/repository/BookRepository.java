package com.lafin.springjpa01.repository;

import com.lafin.springjpa01.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
