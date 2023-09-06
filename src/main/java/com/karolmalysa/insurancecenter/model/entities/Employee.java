package com.karolmalysa.insurancecenter.model.entities;

import com.karolmalysa.insurancecenter.exception.ResourceNotFoundException;
import com.karolmalysa.insurancecenter.model.dao.EmployeeRepository;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "surname", unique = false, nullable = false)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", unique = false, nullable = false)
    private UserRoles role;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", unique = false, nullable = false)
    private String password;

    private static EmployeeRepository employeeRepository;

    public static String deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "employeeId", id.toString()));
        employeeRepository.delete(employee);

        return "Employee data has been deleted successfully...";
    }


//    @ManyToOne
//    @JoinColumn(name = "idClaim", nullable = false)
//    private Claim claim;
}
