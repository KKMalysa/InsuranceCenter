package com.karolmalysa.insurancecenter.model.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "insurance")
@Data
public class Insurance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id autoincrementation
    private Long id;

    @Column(name = "name", length = 80, unique = true, nullable = false)
    private  String name;

    @Column(name = "amount", nullable = false)
    private Long amount = 1L;
    @Column(name = "price", nullable = false)
    private  Float price; //BigDecimal?

    @ManyToOne
    @JoinColumn(name = "idCompanyClient", nullable = false)
    private CompanyClient companyClient;
}
