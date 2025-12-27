package com.gdsc.boilerplate.model;


import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String fullname;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "code")
    private String code;

    @Column(name = "address")
    private String address;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;
}
