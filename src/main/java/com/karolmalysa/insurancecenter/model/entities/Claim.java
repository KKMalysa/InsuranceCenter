package com.karolmalysa.insurancecenter.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "claim")
@Data
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id autoincrementation
    private Long id;

    @Column(name = "dateOfIncident", length = 50, unique = false, nullable = false)
    private Date dateOfIncident;

    @Column(name = "description", length = 500, unique = false, nullable = true)
    private  String description;

    @Column(name = "decision", length = 80, unique = false, nullable = true)
    private  String decision;


    @OneToMany(mappedBy = "claim")
    private List<Employee> employeeList;

    @ManyToOne
    @JoinColumn(name = "idInsurance", nullable = false)
    private Insurance insurance;
}
