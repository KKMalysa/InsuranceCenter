package com.karolmalysa.insurancecenter.model.components;

import com.karolmalysa.insurancecenter.exception.ResourceNotFoundException;
import com.karolmalysa.insurancecenter.model.dao.InsuranceRepository;
import com.karolmalysa.insurancecenter.model.dto.InsuranceDto;
import com.karolmalysa.insurancecenter.model.entities.Insurance;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InsuranceComponnent {

    private static InsuranceRepository insuranceRepository;


    public InsuranceDto saveInsurance (InsuranceDto insuranceDto) {
        Insurance insurance = insuranceRepository.save(insuranceDto.createEntity());

        return new InsuranceDto(insurance);
    }


    public List<InsuranceDto> findAll () {
        return insuranceRepository.findAll().stream()
                .map(insurance -> new InsuranceDto(insurance))
                .collect(Collectors.toList());

    }

    /** pagination */
    public List<InsuranceDto> findAll (Integer pageNumber, Integer pageSize) {
        return insuranceRepository.findAll(PageRequest.of(pageNumber, pageSize)).stream()
                .map(insurance -> new InsuranceDto(insurance))
                .collect(Collectors.toList());

    }

    public List<InsuranceDto> findByPriceBetween (Long minPrice, Long maxPrice ) {
        return insuranceRepository.findByPriceBetween (minPrice.floatValue(),maxPrice.floatValue() ).stream()
                .map(insurance -> new InsuranceDto(insurance))
                .collect(Collectors.toList());


    }

    public static String deleteInsurance(Long id) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance", "insuranceId", id.toString()));
        insuranceRepository.delete(insurance);

        return "Insurance data has been deleted successfully...";
    }



}
