package com.karolmalysa.insurancecenter.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "surname", unique = false, nullable = false)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", unique = false, nullable = false)
    private UserRoles role;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

//    @ManyToOne
//    @JoinColumn(name = "idClaim", nullable = false)
//    private Claim claim;
}
