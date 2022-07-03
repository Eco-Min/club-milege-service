package com.triple.clubmileageservice.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name="user", indexes = @Index(name="i_userId", columnList = "user_id"))
@NoArgsConstructor
public class UserEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "user_id",unique = true)
    private String userId;

    @Column(name = "user_email",nullable = false, unique = true)
    private String email;

    public UserEntity(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
