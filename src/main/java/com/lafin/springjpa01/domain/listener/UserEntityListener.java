package com.lafin.springjpa01.domain.listener;

import com.lafin.springjpa01.domain.User;
import com.lafin.springjpa01.domain.UserHistory;
import com.lafin.springjpa01.repository.UserHistoryRepository;
import com.lafin.springjpa01.support.BeanUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {

    @PrePersist
    @PreUpdate
    void prePersistAndPreUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
