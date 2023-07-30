package com.karolmalysa.insurancecenter.model.dto;

import com.karolmalysa.insurancecenter.model.entities.CompanyClient;
import com.karolmalysa.insurancecenter.model.entities.Insurance;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class InsuranceDto {


    private Long id;

    private  String name;

    private Long amount = 1L;

    private  Float price; //BigDecimal?

    private Long idCompanyClient;


    public Insurance createEntity() {

        CompanyClient companyClient = new CompanyClient();
        companyClient.setId(this.idCompanyClient);
        Insurance insurance = new Insurance();

        insurance.setName(this.name);
        insurance.setAmount(this.amount);
        insurance.setPrice(this.price);
        insurance.setCompanyClient(companyClient); //podając samo id silnik doda relacje do tego klienta nawet jak się go nie pobierze z bazy danych. thanks K.W. ;)


        return insurance;
    }

    public InsuranceDto(Insurance insurance) {
        this.id = insurance.getId();
        this.name = insurance.getName();
        this.amount = insurance.getAmount();
       this.price = insurance.getPrice();
        this.idCompanyClient = insurance.getCompanyClient().getId();
    }
}
