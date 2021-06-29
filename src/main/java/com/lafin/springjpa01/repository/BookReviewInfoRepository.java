package com.lafin.springjpa01.repository;

import com.lafin.springjpa01.domain.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewInfoRepository extends JpaRepository<BookReviewInfo, Long> {

}
