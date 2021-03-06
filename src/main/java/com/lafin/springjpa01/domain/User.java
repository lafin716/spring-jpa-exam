package com.lafin.springjpa01.domain;


import com.lafin.springjpa01.domain.listener.Auditable;
import com.lafin.springjpa01.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@Table(name = "user", indexes = { @Index(columnList = "name") }, uniqueConstraints = {@UniqueConstraint( columnNames = {"email"} )})
@EntityListeners(value = { UserEntityListener.class })
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

//    @Column(updatable = false)
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @Column(insertable = false)
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
//
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

    // 디비의 영속성에 추가되지 않는다
//    @Transient
//    private String testData;


}
