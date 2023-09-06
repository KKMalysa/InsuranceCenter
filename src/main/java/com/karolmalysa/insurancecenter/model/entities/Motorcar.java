package com.karolmalysa.insurancecenter.model.entities;

import com.karolmalysa.insurancecenter.exception.ResourceNotFoundException;
import com.karolmalysa.insurancecenter.model.dao.MotorcarRepository;
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

    private static MotorcarRepository motorcarRepository;

    public static String deleteMotorcar(Long id) {
        Motorcar motorcar = motorcarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Motorcar", "motorcarId", id.toString()));
        motorcarRepository.delete(motorcar);

        return "Motorcar data has been deleted successfully...";
    }
}
