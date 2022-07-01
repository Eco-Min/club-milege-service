package com.triple.clubmileageservice.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="user")
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

    public UserEntity() {

    }
}
