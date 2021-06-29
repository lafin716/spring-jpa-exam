package com.lafin.springjpa01.domain.listener;

import javax.persistence.PrePersist;

public class MyEntityListener {

    @PrePersist
    public void prePersist(Object o) {

        if (o instanceof Auditable) {
            ((Auditable) o).getCreatedAt();
        }
    }
}
