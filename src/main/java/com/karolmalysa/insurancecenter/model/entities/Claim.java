package com.karolmalysa.insurancecenter.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus;

    @Column(name = "file_content")
    @Lob
    private byte[] fileContent;

    @Column(name = "image_content")
    @Lob
    private byte[] imageContent;


//    @OneToMany(mappedBy = "claim")
//    private List<Employee> employeeList;

    @ManyToOne
    @JoinColumn(name = "idInsurance", nullable = false)
    private Insurance insurance;
}
