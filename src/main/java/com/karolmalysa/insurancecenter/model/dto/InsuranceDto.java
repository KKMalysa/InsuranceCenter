package com.karolmalysa.insurancecenter.model.dto;

import com.karolmalysa.insurancecenter.model.entities.Claim;
import com.karolmalysa.insurancecenter.model.entities.Insurance;
import com.karolmalysa.insurancecenter.model.entities.Motorcar;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class InsuranceDto {


    private Long id;

    private  String name;

    private Date startDate;

    private Date endDate;
    private  Float price; //BigDecimal?

    private Long idMotorcar;

    private List<Long> claimList;


    public Insurance createEntity() {

        Motorcar motorcar = new Motorcar();
        motorcar.setId(this.idMotorcar);
        Insurance insurance = new Insurance();

        insurance.setName(this.name);
        insurance.setStartDate(this.startDate);
        insurance.setEndDate(this.endDate);
        insurance.setPrice(this.price);
        insurance.setMotorcar(motorcar);            // thanks K.W. ;)
        insurance.setClaimList(new ArrayList<>());

        return insurance;
    }

    public InsuranceDto(Insurance insurance) {
        this.id = insurance.getId();
        this.name = insurance.getName();
        this.startDate = insurance.getStartDate();
        this.endDate = insurance.getEndDate();
        this.price = insurance.getPrice();
        this.idMotorcar = insurance.getMotorcar().getId();
        this.claimList = insurance.getClaimList().stream().map(Claim::getId).collect(Collectors.toList());
    }
}
