package com.karolmalysa.insurancecenter.model.components;

import com.karolmalysa.insurancecenter.model.dao.CompanyClientRepository;
import com.karolmalysa.insurancecenter.model.dto.CompanyClientDto;
import com.karolmalysa.insurancecenter.model.entities.CompanyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyClientComponnent {

    private final CompanyClientRepository companyClientRepository;


    public CompanyClientDto saveCompanyClient (CompanyClientDto companyClientDto) {
        CompanyClient companyClient = companyClientRepository.save(companyClientDto.createEntity());

        return new CompanyClientDto(companyClient);
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

    /**
     * @TODO  (futurede velop)
     *  If @param productNumber is <1 than comes a risk of potential fraud.
     *  emplyee needs to check, if the client is getting multiple insurance for the same object,
     *  if so, than there needs to be a currency period, if no, than he can get a discount for a frequent customer.
     *
     */
    public List<CompanyClientDto> findMultipleInsuredClients () {
        return companyClientRepository.findAll().stream().filter(companyClient -> companyClient.getMotorcarList().size() > 1)
                .map(CompanyClientDto::new)
                .collect(Collectors.toList());

    }



}
