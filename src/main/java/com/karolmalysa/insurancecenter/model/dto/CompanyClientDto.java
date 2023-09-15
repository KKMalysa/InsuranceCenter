package com.karolmalysa.insurancecenter.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.karolmalysa.insurancecenter.model.entities.CompanyClient;
import com.karolmalysa.insurancecenter.model.entities.Motorcar;
import com.karolmalysa.insurancecenter.model.entities.UserRoles;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Data
@NoArgsConstructor
public class CompanyClientDto {


    private Long id;

    @NotBlank(message = "This field cannot be blank!!")
    private  String firstName;

    @NotBlank(message = "This field cannot be blank!!")
    private  String lastName;

    @NotBlank(message = "This field cannot be blank!!")
    private UserRoles role;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date of birth have to be in the Past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "This field cannot be blank!!")
    private String pesel;

    @NotBlank(message = "This field cannot be blank!!")
    private String address;

    @NotBlank(message = "This field cannot be blank!!")
    @Size(min = 9, max = 9, message = "Phone Number must be only 9 digits!!")
    private String phone;

    @Email(message = "Email is not valid!!")
    @NotBlank(message = "Email is required!!")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;

    private List<Long> motorcarList;



    public CompanyClient createEntity() {

        CompanyClient companyClient = new CompanyClient();

        companyClient.setName(this.firstName);
        companyClient.setSurname(this.lastName);
        companyClient.setPesel(this.pesel);
        companyClient.setRole(this.role);
        companyClient.setAddress(this.address);
        companyClient.setPhone(this.phone);
        companyClient.setEmail(this.email);
        companyClient.setMotorcarList(new ArrayList<>());

        return companyClient;
    }

    public CompanyClientDto(CompanyClient companyClient) {
        this.id = companyClient.getId();
        this.firstName = companyClient.getName();
        this.lastName = companyClient.getSurname();
       this.pesel = companyClient.getPesel();
       this.role = companyClient.getRole();
       this.address = companyClient.getAddress();
       this.phone = companyClient.getPhone();
       this.email = companyClient.getEmail();
       this.motorcarList = companyClient.getMotorcarList().stream().map(Motorcar::getId).collect(Collectors.toList());
    }
}
