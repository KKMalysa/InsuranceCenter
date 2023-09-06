package com.karolmalysa.insurancecenter.model.entities;

import com.karolmalysa.insurancecenter.exception.ResourceNotFoundException;
import com.karolmalysa.insurancecenter.model.dao.InsuranceRepository;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "insurance")
@Data
public class Insurance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = false, nullable = false, length = 80)
    private  String name;

    @Column(name = "startdate", unique = false, nullable = false)
    private Date startDate;

    @Column(name = "enddate", unique = false, nullable = false)
    private Date endDate;

    @Column(name = "price", unique = false, nullable = false)
    private  Float price; //BigDecimal?

    @OneToMany(mappedBy = "insurance")
    private List<Claim> claimList;

    @ManyToOne
    @JoinColumn(name = "idMotorcar", nullable = false)
    private Motorcar motorcar;

    private static InsuranceRepository insuranceRepository;

    public static String deleteInsurance(Long id) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance", "insuranceId", id.toString()));
        insuranceRepository.delete(insurance);

        return "Insurance data has been deleted successfully...";
    }
}
