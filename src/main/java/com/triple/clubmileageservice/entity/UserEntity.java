package com.triple.clubmileageservice.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table
public class UserEntity extends BaseTimeEntity{

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "user_email",nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    List<ReviewEntity> reviews;
}
