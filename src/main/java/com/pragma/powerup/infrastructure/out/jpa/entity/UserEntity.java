package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String lastName;
    private Integer  identityDocument;
    @Column(length = 13)
    private String phone;
    private Date birthDate;
    @Column(length = 50)
    private String email;
    private Integer roleId;
    @Column(length = 50)
    private String password;
}
