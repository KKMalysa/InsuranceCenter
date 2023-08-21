package com.karolmalysa.insurancecenter.model.components;

import com.karolmalysa.insurancecenter.exception.ResourceNotFoundException;
import com.karolmalysa.insurancecenter.model.dao.CompanyClientRepository;
import com.karolmalysa.insurancecenter.model.dto.CompanyClientDto;
import com.karolmalysa.insurancecenter.model.entities.CompanyClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyClientComponnentTest {

    @Mock
    private CompanyClientRepository companyClientRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CompanyClientComponnent companyClientComponnent;

    @Test
    public void testFindCompanyClientByIdNotFound() {
        when(companyClientRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> companyClientComponnent.findCompanyClientById(1L));
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        when(companyClientRepository.findByEmail(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> companyClientComponnent.loadUserByUsername("test@email.com"));
    }

    @Test
    public void testLoadUserByUsernameFound() {
        CompanyClient companyClient = new CompanyClient();
        companyClient.setPassword("testPassword");

        when(companyClientRepository.findByEmail(anyString())).thenReturn(companyClient);

        UserDetails userDetails = companyClientComponnent.loadUserByUsername("test@email.com");

        assertEquals("test@email.com", userDetails.getUsername());
        assertEquals("testPassword", userDetails.getPassword());
    }

    @Test
    public void testAddCompanyClientDateNotValid() {
        CompanyClientDto companyClientDto = new CompanyClientDto();
        companyClientDto.setDateOfBirth(LocalDate.now().plusDays(1));

        assertThrows(ResourceNotFoundException.class, () -> companyClientComponnent.addCompanyClient(companyClientDto));
    }

    @Test
    public void testFindByEmailNotFound() {
        when(companyClientRepository.findByEmail(anyString())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> companyClientComponnent.findByEmail("test@email.com"));
    }


    @Test
    public void testUpdateClientInfoEmailNotNull() {
        CompanyClientDto companyClientDto = new CompanyClientDto();
        companyClientDto.setEmail("test@email.com");

        assertThrows(ResourceNotFoundException.class, () -> companyClientComponnent.updateClientInfo(companyClientDto, 1L));
    }


    @Test
    public void testDeleteClientNotFound() {
        when(companyClientRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> companyClientComponnent.deleteClient(1L));
    }

    @Test
    public void testDeleteClientFound() {
        CompanyClient companyClient = new CompanyClient();

        when(companyClientRepository.findById(anyLong())).thenReturn(Optional.of(companyClient));

        String result = companyClientComponnent.deleteClient(1L);

        assertEquals("CompanyClient Data has been deleted successfully...", result);
    }


}

