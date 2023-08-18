package com.karolmalysa.insurancecenter.restcontrollers;

import com.karolmalysa.insurancecenter.model.components.EmployeeComponent;
import com.karolmalysa.insurancecenter.model.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeComponent employeeComponent;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeComponent.saveEmployee(employeeDto);
    }

    @GetMapping(value = "all", produces = "application/json")
    public List<EmployeeDto> findAllEmployees() {
        return  employeeComponent.findAll();
    }

    @GetMapping(produces = "application/json")
    public List<EmployeeDto> findAllEmployeesWithPagination(@PathParam("page") Integer pageNumber, @PathParam("size") Integer pageSize) {

        return  employeeComponent.findAll(pageNumber, pageSize);
    }

}
