package com.getcat.api.model;

import jakarta.persistence.*;
import lombok.*; // this library will spare us from writing getters and setters and constructors

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(nullable = false, length = 100)
    private String firstname;

    @Column(nullable = false, length = 100)
    private String lastname;

    @Column(unique = true, nullable = false, length = 254)
    private String email;

    @Column(unique = true, length = 20)
    private String phone;

    @Column(name = "user_photo", length = 500)
    private String userPhoto;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "is_online")
    private Boolean isOnline = false;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "is_blocked")
    private Boolean isBlocked = false;

    //@ManyToOne is the annotation for FK
    @ManyToOne(fetch = FetchType.LAZY) //lazy fetch will not get address details without asking
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
}