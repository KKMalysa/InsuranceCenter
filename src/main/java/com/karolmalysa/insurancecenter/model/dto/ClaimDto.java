package com.karolmalysa.insurancecenter.model.dto;

import com.karolmalysa.insurancecenter.model.entities.Claim;
import com.karolmalysa.insurancecenter.model.entities.ClaimStatus;
import com.karolmalysa.insurancecenter.model.entities.Insurance;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class ClaimDto {


    private Long id;

    private Date dateOfIncident;

    private  String description;

    private ClaimStatus claimStatus;

    private  Long idInsurance;

    private byte[] fileContent;

    private byte[] imageContent;
    private BigDecimal amount;
    private LocalDate dateFiled;

//    private  List<Long> employeeList;


    public Claim createEntity() {

        Insurance insurance = new Insurance();
        insurance.setId(this.idInsurance);
        Claim claim = new Claim();

        claim.setDateOfIncident(this.dateOfIncident);
        claim.setDescription(this.description);
        claim.setClaimStatus(this.claimStatus);
        claim.setInsurance(insurance);
        claim.setFileContent(this.fileContent);
        claim.setImageContent(this.imageContent);
//        claim.setEmployeeList(new ArrayList<>());

        return claim;
    }

    public ClaimDto(Claim claim) {
        this.id = claim.getId();
        this.dateOfIncident = claim.getDateOfIncident();
        this.description = claim.getDescription();
        this.claimStatus = claim.getClaimStatus();
        this.idInsurance = claim.getInsurance().getId();
        this.fileContent = claim.getFileContent();
        this.imageContent = claim.getImageContent();
    }

    public void setStatus(ClaimStatus status) {
        this.claimStatus = status;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDateFiled(LocalDate dateFiled) {
        this.dateFiled = dateFiled;
    }

}
