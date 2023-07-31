package com.karolmalysa.insurancecenter.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "motorcar")
@Data
public class Motorcar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id autoincrementation
    private Long id;

    @Column(name = "brand", length = 80, unique = false, nullable = false)
    private  String brand;

    @Column(name = "model", length = 80, unique = false, nullable = false)
    private  String model;

    @Column(name = "vin", length = 80, unique = true, nullable = false)
    private  String vin;

    @Column(name = "year", unique = false, nullable = false)
    private String year;


    @OneToMany(mappedBy = "motorcar")
    private List<Insurance> insuranceList;

    @ManyToOne
    @JoinColumn(name = "idCompanyClient", nullable = false)
    private CompanyClient companyClient;
}
