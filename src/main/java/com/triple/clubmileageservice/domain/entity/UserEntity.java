package com.triple.clubmileageservice.domain.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name="user")
public class UserEntity extends BaseTimeEntity{

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "user_email",nullable = false, unique = true)
    private String email;
}
