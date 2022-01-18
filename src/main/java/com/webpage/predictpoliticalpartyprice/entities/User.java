package com.webpage.predictpoliticalpartyprice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false, length = 64)
    private String password;

    @Getter
    @Setter
    @Column(name = "username", nullable = false, length = 20,unique = true)
    private String username;

}
