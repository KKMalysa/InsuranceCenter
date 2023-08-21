package com.karolmalysa.insurancecenter.model.components;

import com.karolmalysa.insurancecenter.exception.ResourceNotFoundException;
import com.karolmalysa.insurancecenter.model.dao.CompanyClientRepository;
import com.karolmalysa.insurancecenter.model.dto.CompanyClientDto;
import com.karolmalysa.insurancecenter.model.entities.CompanyClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyClientComponnent implements UserDetailsService {


    private final CompanyClientRepository companyClientRepository;


    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;



    public CompanyClientDto saveCompanyClient (CompanyClientDto companyClientDto) {
        CompanyClient companyClient = companyClientRepository.save(companyClientDto.createEntity());

        return new CompanyClientDto(companyClient);
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CompanyClient companyClient = companyClientRepository.findByEmail(email);
        if (companyClient == null) {
                throw new UsernameNotFoundException("No user found with email: " + email);
        }

        return User.builder()
                .username(email)
                .password(companyClient.getPassword())
                .roles("CLIENT_PREMIUM")
                .build();
    }


    public CompanyClientDto addCompanyClient(CompanyClientDto companyClientDto) {
        if (LocalDate.now().isBefore(companyClientDto.getDateOfBirth()))
            throw new ResourceNotFoundException("The date is not valid. Please provide the date in past.");
        CompanyClient companyClientData = modelMapper.map(companyClientDto, CompanyClient.class);
        companyClientData.setPassword(passwordEncoder.encode(companyClientData.getPassword()));
        CompanyClient savedClient = companyClientRepository.save(companyClientData);
        return modelMapper.map(savedClient, CompanyClientDto.class);
    }

    public List<CompanyClientDto> findAll () {
        return companyClientRepository.findAll().stream()
                .map(CompanyClientDto::new)
                .collect(Collectors.toList());

    }
                                                                                                                         //thanks KW

    public List<CompanyClientDto> findAll (Integer pageNumber, Integer pageSize) {
        return companyClientRepository.findAll(PageRequest.of(pageNumber, pageSize)).stream()
                .map(CompanyClientDto::new)
                .collect(Collectors.toList());

    }

    public List<CompanyClientDto> findMultipleInsuredClients () {
        return companyClientRepository.findAll().stream().filter(companyClient -> companyClient.getMotorcarList().size() > 1)
                .map(CompanyClientDto::new)
                .collect(Collectors.toList());
    }


    public CompanyClientDto findCompanyClientById(Long companyClientId) {
        CompanyClient companyClient = companyClientRepository.findById(companyClientId)
                .orElseThrow(() -> new ResourceNotFoundException("CompanyClient ", "companyClientId", "" + companyClientId));
        return modelMapper.map(companyClient, CompanyClientDto.class);
    }
//    public CompanyClientDto findCompanyClientById(Long companyClientId) {
//        CompanyClient companyClient = companyClientRepository.findById(companyClientId)
//                .orElseThrow(() -> new ResourceNotFoundException("CompanyClient not found with ID: " + companyClientId));
//
//        return modelMapper.map(companyClient, CompanyClientDto.class);
//    }

    public CompanyClientDto findByEmail(String email) {
        CompanyClient companyClient = companyClientRepository.findByEmail(email);

        if (companyClient == null) {
            throw new ResourceNotFoundException("Client ", "clientId", email);
        }

        return modelMapper.map(companyClient, CompanyClientDto.class);
    }

    public CompanyClientDto updateClientInfo(CompanyClientDto companyClient, Long clientId) {
        if (companyClient.getEmail() != null)
            throw new ResourceNotFoundException("email is not changeble, plese remove email in json data...");

        CompanyClient prevClient = companyClientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("CompanyClient ", "companyClientId", "" + companyClient.getId()));
        CompanyClient updatedClient = modelMapper.map(companyClient, CompanyClient.class);
        updatedClient.setId(companyClient.getId());
        updatedClient.setEmail(prevClient.getEmail());
        updatedClient.setPassword(passwordEncoder.encode(updatedClient.getPassword()));
        CompanyClient updatedClientdata = companyClientRepository.save(updatedClient);
        return modelMapper.map(updatedClientdata, CompanyClientDto.class);
    }

    public String deleteClient(Long id) {
        CompanyClient client = companyClientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CompanyClient ", "companyClientId", "" + id));
        companyClientRepository.delete(client);

        return "CompanyClient Data has been deleted successfully...";

    }


}
