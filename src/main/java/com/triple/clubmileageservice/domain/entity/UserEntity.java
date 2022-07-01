package com.triple.clubmileageservice.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name="user")
@NoArgsConstructor
public class UserEntity extends BaseTimeEntity{

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "user_email",nullable = false, unique = true)
    private String email;

    public UserEntity(String id, String email) {
        this.id = id;
        this.email = email;
    }
}
