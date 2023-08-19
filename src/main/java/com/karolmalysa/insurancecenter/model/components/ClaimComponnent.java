package com.karolmalysa.insurancecenter.model.components;

import com.karolmalysa.insurancecenter.exception.ResourceNotFoundException;
import com.karolmalysa.insurancecenter.model.dao.ClaimRepository;
import com.karolmalysa.insurancecenter.model.dto.ClaimDto;
import com.karolmalysa.insurancecenter.model.entities.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;



import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ClaimComponnent {

    private final ClaimRepository claimRepository;

    private ModelMapper modelMapper;


    public ClaimDto saveClaim (ClaimDto claimDto) {
        Claim claim = claimRepository.save(claimDto.createEntity());

        return new ClaimDto(claim);
    }

    public List<ClaimDto> findAll () {
        return claimRepository.findAll().stream()
                .map(claim -> new ClaimDto(claim))
                .collect(Collectors.toList());

    }


    public List<ClaimDto> findAll (Integer pageNumber, Integer pageSize) {
        return claimRepository.findAll(PageRequest.of(pageNumber, pageSize)).stream()
                .map(claim -> new ClaimDto(claim))
                .collect(Collectors.toList());
    }

    public Claim getClaimById(Long claimId ) {
        return claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim ", "Claim id", "" + claimId));
    }

    public String deleteClaim(Long claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim ", "Claim id", "" + claimId));
        claimRepository.delete(claim);
        return "claim info delete successfully...";
    }

    public Claim updateClaim(Claim claim, Long claimId) {
        Claim claime = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim ", "Claim id", "" + claimId));
        Claim newClaim = modelMapper.map(claim, Claim.class);
        newClaim.setId(claimId);
        newClaim.setDateOfIncident(claime.getDateOfIncident());

        return claimRepository.save(newClaim);
    }

}
