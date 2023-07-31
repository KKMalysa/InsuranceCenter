package com.karolmalysa.insurancecenter.model.components;

import com.karolmalysa.insurancecenter.model.dao.ClaimRepository;
import com.karolmalysa.insurancecenter.model.dto.ClaimDto;
import com.karolmalysa.insurancecenter.model.dto.MotorcarDto;
import com.karolmalysa.insurancecenter.model.entities.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ClaimComponnent {

    private final ClaimRepository claimRepository;

    public ClaimDto saveClaim (ClaimDto claimDto) {
        Claim claim = claimRepository.save(claimDto.createEntity());

        return new ClaimDto(claim);
    }

    public List<ClaimDto> findAll () {
        return claimRepository.findAll().stream()
                .map(claim -> new ClaimDto(claim))
                .collect(Collectors.toList());

    }

    /** pagination */
    public List<ClaimDto> findAll (Integer pageNumber, Integer pageSize) {
        return claimRepository.findAll(PageRequest.of(pageNumber, pageSize)).stream()
                .map(claim -> new ClaimDto(claim))
                .collect(Collectors.toList());
    }



}
