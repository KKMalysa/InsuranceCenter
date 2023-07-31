package com.karolmalysa.insurancecenter.model.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CompanyClient")
@Data
public class CompanyClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "surname", unique = false, nullable = false)
    private String surname;

    @Column(name = "PESEL", unique = true, nullable = false)
    private String pesel;

    @Column(name = "address", unique = false, nullable = false)
    private String address;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @OneToMany(mappedBy = "companyClient")
    private List<Motorcar> motorcarList;

}
