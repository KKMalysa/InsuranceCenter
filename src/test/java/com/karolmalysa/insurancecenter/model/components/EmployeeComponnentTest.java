package com.karolmalysa.insurancecenter.model.components;

import com.karolmalysa.insurancecenter.model.dao.EmployeeRepository;
import com.karolmalysa.insurancecenter.model.dto.EmployeeDto;
import com.karolmalysa.insurancecenter.model.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeComponnentTest {

    @InjectMocks
    private EmployeeComponnent employeeComponnent;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveEmployee() {
        // Given
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = new Employee();
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // When
        EmployeeDto result = employeeComponnent.saveEmployee(employeeDto);

        // Then
        assertNotNull(result);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testFindAll() {
        // Given
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeRepository.findAll()).thenReturn(employees);

        // When
        List<EmployeeDto> results = employeeComponnent.findAll();

        // Then
        assertEquals(2, results.size());
    }

    @Test
    public void testFindAllWithPagination() {
        // Given
        Page<Employee> employees = new PageImpl<>(Arrays.asList(new Employee(), new Employee()));
        when(employeeRepository.findAll(PageRequest.of(0, 2))).thenReturn(employees);

        // When
        List<EmployeeDto> results = employeeComponnent.findAll(0, 2);

        // Then
        assertEquals(2, results.size());
    }

    @Test
    public void testLoadUserByUsername() {
        // Given
        String email = "test@example.com";
        Employee employee = new Employee();
        employee.setSurname("Smith");
        when(employeeRepository.findByEmail(email)).thenReturn(employee);

        // When
        UserDetails userDetails = employeeComponnent.loadUserByUsername(email);

        // Then
        assertEquals(email, userDetails.getUsername());
        assertEquals("Smith", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(role -> "ADMIN".equals(role.getAuthority())));
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        // Given
        when(employeeRepository.findByEmail("test@example.com")).thenReturn(null);

        // When & Then
        assertThrows(UsernameNotFoundException.class, () -> employeeComponnent.loadUserByUsername("test@example.com"));
    }
}

