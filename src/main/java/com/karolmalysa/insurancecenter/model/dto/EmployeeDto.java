package com.karolmalysa.insurancecenter.model.dto;

import com.karolmalysa.insurancecenter.model.entities.Claim;
import com.karolmalysa.insurancecenter.model.entities.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EmployeeDto {


    private Long id;

    private String name;

    private String surname;

    private String role;

    private String phone;

    private  Long idClaim;


    public Employee createEntity() {

        Claim claim = new Claim();
        claim.setId(this.idClaim);
        Employee employee = new Employee();

        employee.setName(this.name);
        employee.setSurname(this.surname);
        employee.setRole(this.role);
        employee.setPhone(this.phone);
        employee.setClaim(claim);


        return employee;
    }

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.role = employee.getRole();
        this.phone = employee.getPhone();
        this.idClaim = employee.getClaim().getId();
    }
}
