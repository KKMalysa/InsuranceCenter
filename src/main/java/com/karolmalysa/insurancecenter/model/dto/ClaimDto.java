package com.karolmalysa.insurancecenter.model.dto;

import com.karolmalysa.insurancecenter.model.entities.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ClaimDto {


    private Long id;

    private Date dateOfIncident;

    private  String description;

    private ClaimStatus claimStatus;

    private  Long idInsurance;

//    private  List<Long> employeeList;


    public Claim createEntity() {

        Insurance insurance = new Insurance();
        insurance.setId(this.idInsurance);
        Claim claim = new Claim();

        claim.setDateOfIncident(this.dateOfIncident);
        claim.setDescription(this.description);
        claim.setClaimStatus(this.claimStatus);
        claim.setInsurance(insurance);
//        claim.setEmployeeList(new ArrayList<>());

        return claim;
    }

    public ClaimDto(Claim claim) {
        this.id = claim.getId();
        this.dateOfIncident = claim.getDateOfIncident();
        this.description = claim.getDescription();
        this.claimStatus = claim.getClaimStatus();
        this.idInsurance = claim.getInsurance().getId();
    }

}
