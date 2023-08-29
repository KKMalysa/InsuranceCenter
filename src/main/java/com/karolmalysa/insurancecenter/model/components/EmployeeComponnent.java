package com.karolmalysa.insurancecenter.model.components;

import com.karolmalysa.insurancecenter.model.dao.EmployeeRepository;
import com.karolmalysa.insurancecenter.model.dto.EmployeeDto;
import com.karolmalysa.insurancecenter.model.entities.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeComponnent implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDto saveEmployee (EmployeeDto employeeDto) {
        Employee employee = employeeRepository.save(employeeDto.createEntity());

        return new EmployeeDto(employee);
    }

    public List<EmployeeDto> findAll () {
        return employeeRepository.findAll().stream()
                .map(EmployeeDto::new)
                .collect(Collectors.toList());

    }

    public List<EmployeeDto> findAll (Integer pageNumber, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(pageNumber, pageSize)).stream()
                .map(EmployeeDto::new)
                .collect(Collectors.toList());

    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("Nie znaleziono pracownika o takim e-mailu" + email);
        }
        return User.builder()
                .username(email)
                .password(employee.getSurname()) // surname as a password - just to make it easy
                .roles("ADMIN") // each employee is an admin - just to make it easy
                .build();
    }

}
