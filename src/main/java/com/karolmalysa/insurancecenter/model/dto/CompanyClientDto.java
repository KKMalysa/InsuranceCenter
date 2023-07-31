package com.karolmalysa.insurancecenter.model.dto;

import com.karolmalysa.insurancecenter.model.entities.CompanyClient;
import com.karolmalysa.insurancecenter.model.entities.Motorcar;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class CompanyClientDto {


    private Long id;

    private  String name;

    private  String surname;

    private String pesel;

    private String address;

    private String phone;

    private List<Long> motorcarList;



    public CompanyClient createEntity() {

        CompanyClient companyClient = new CompanyClient();

        companyClient.setName(this.name);
        companyClient.setSurname(this.surname);
        companyClient.setPesel(this.pesel);
        companyClient.setAddress(this.address);
        companyClient.setPhone(this.phone);
        companyClient.setMotorcarList(new ArrayList<>());

        return companyClient;
    }

    public CompanyClientDto(CompanyClient companyClient) {
        this.id = companyClient.getId();
        this.name = companyClient.getName();
        this.surname = companyClient.getSurname();
       this.pesel = companyClient.getPesel();
       this.address = companyClient.getAddress();
       this.phone = companyClient.getPhone();
       this.motorcarList = companyClient.getMotorcarList().stream().map(Motorcar::getId).collect(Collectors.toList());
    }
}
