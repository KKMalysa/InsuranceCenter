package com.karolmalysa.insurancecenter.model.dto;

import com.karolmalysa.insurancecenter.model.entities.CompanyClient;
import com.karolmalysa.insurancecenter.model.entities.Insurance;
import com.karolmalysa.insurancecenter.model.entities.Motorcar;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MotorcarDto {

    private Long id;

    private  String brand;

   private  String model;

    private  String vin;

    private String year;
    private Long idCompanyClient;

    private List<Long> insuranceList;


    public Motorcar createEntity() {

        CompanyClient companyClient = new CompanyClient();
        companyClient.setId(this.idCompanyClient);
        Motorcar motorcar = new Motorcar();

        motorcar.setBrand(this.brand);
        motorcar.setModel(this.model);
        motorcar.setVin(this.vin);
        motorcar.setYear(this.year);
        motorcar.setCompanyClient(companyClient);
        motorcar.setInsuranceList(new ArrayList<>());

        return  motorcar;
    }

    public MotorcarDto(Motorcar motorcar) {
        this.id = motorcar.getId();
        this.brand = motorcar.getBrand();
        this.model = motorcar.getModel();
        this.vin = motorcar.getVin();
        this.year = motorcar.getYear();
        this.idCompanyClient = motorcar.getCompanyClient().getId();
        this.insuranceList = motorcar.getInsuranceList().stream().map(Insurance::getId).collect(Collectors.toList());
    }


}
