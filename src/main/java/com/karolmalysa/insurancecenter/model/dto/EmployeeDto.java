package com.karolmalysa.insurancecenter.model.dto;

import com.karolmalysa.insurancecenter.model.entities.Claim;
import com.karolmalysa.insurancecenter.model.entities.Employee;
import com.karolmalysa.insurancecenter.model.entities.UserRoles;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
public class EmployeeDto {


    private Long id;
    @NotBlank(message = "This field cannot be blank!!")
    private String name;
    @NotBlank(message = "This field cannot be blank!!")
    private String surname;
    @NotBlank(message = "This field cannot be blank!!")
    private UserRoles role;
    @NotBlank(message = "This field cannot be blank!!")
    @Size(min = 9, max = 9, message = "Phone Number must be only 9 digits!!")
    private String phone;
    @Email(message = "Email is not valid!!")
    @NotBlank(message = "Email is required!!")
    private String email;

//    private  Long idClaim;


    public Employee createEntity() {

        Claim claim = new Claim();

        Employee employee = new Employee();
        employee.setName(this.name);
        employee.setSurname(this.surname);
        employee.setRole(this.role);
        employee.setPhone(this.phone);
        employee.setEmail(this.email);

        return employee;
    }

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.role = employee.getRole();
        this.phone = employee.getPhone();
        this.email = employee.getEmail();
    }
}
