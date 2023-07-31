package com.karolmalysa.insurancecenter.model.components;

import com.karolmalysa.insurancecenter.model.dao.EmployeeRepository;
import com.karolmalysa.insurancecenter.model.dto.EmployeeDto;
import com.karolmalysa.insurancecenter.model.entities.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeComponnent {

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

}
