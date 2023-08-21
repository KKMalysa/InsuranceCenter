package com.karolmalysa.insurancecenter.model.components;

import com.karolmalysa.insurancecenter.exception.ResourceNotFoundException;
import com.karolmalysa.insurancecenter.model.dao.ClaimRepository;
import com.karolmalysa.insurancecenter.model.entities.Claim;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClaimComponentTest {

    @Mock
    private ClaimRepository claimRepository;

    @InjectMocks
    private ClaimComponnent claimComponnent;


    @Test
    public void testGetClaimById() {
        // given
        Claim claim = new Claim();
        Long claimId = 1L;
        when(claimRepository.findById(claimId)).thenReturn(Optional.of(claim));

        // when
        Claim foundClaim = claimComponnent.getClaimById(claimId);

        // then
        assertNotNull(foundClaim);
        verify(claimRepository, times(1)).findById(claimId);
    }

    @Test
    public void testDeleteClaim() {
        // given
        Claim claim = new Claim();
        Long claimId = 1L;
        when(claimRepository.findById(claimId)).thenReturn(Optional.of(claim));

        // when
        String result = claimComponnent.deleteClaim(claimId);

        // then
        assertEquals("claim info delete successfully...", result);
        verify(claimRepository, times(1)).findById(claimId);
        verify(claimRepository, times(1)).delete(claim);
    }




    // Negative test
    @Test
    public void testGetClaimById_NotFound() {
        // given
        Long claimId = 1L;
        when(claimRepository.findById(claimId)).thenReturn(Optional.empty());

        // when/then
        assertThrows(ResourceNotFoundException.class, () -> claimComponnent.getClaimById(claimId));
        verify(claimRepository, times(1)).findById(claimId);
    }

    // Negative test
    @Test
    public void testDeleteClaim_NotFound() {
        // given
        Long claimId = 1L;
        when(claimRepository.findById(claimId)).thenReturn(Optional.empty());

        // when/then
        assertThrows(ResourceNotFoundException.class, () -> claimComponnent.deleteClaim(claimId));
        verify(claimRepository, times(1)).findById(claimId);
    }

    // Negative test
    @Test
    public void testUpdateClaim_NotFound() {
        // given
        Long claimId = 1L;
        Claim newClaimData = new Claim();
        when(claimRepository.findById(claimId)).thenReturn(Optional.empty());

        // when/then
        assertThrows(ResourceNotFoundException.class, () -> claimComponnent.updateClaim(newClaimData, claimId));
        verify(claimRepository, times(1)).findById(claimId);
    }


}
