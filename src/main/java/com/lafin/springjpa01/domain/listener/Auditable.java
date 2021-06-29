package com.lafin.springjpa01.domain.listener;

import java.time.LocalDateTime;

public interface Auditable {
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
