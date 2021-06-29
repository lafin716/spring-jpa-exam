package com.lafin.springjpa01.repository;

import com.lafin.springjpa01.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {

}
