package com.karolmalysa.insurancecenter.model.dto;

import com.karolmalysa.insurancecenter.model.entities.Claim;
import com.karolmalysa.insurancecenter.model.entities.ClaimStatus;
import com.karolmalysa.insurancecenter.model.entities.Insurance;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ClaimDtoTest {

    @Test
    public void testCreateEntity() {
        // given
        ClaimDto dto = new ClaimDto();
        dto.setDateOfIncident(new Date());
        dto.setDescription("Test description");
        dto.setClaimStatus(ClaimStatus.APPROVED);
        dto.setIdInsurance(1L);
        dto.setFileContent(new byte[]{1, 2, 3, 4});
        dto.setImageContent(new byte[]{5, 6, 7, 8});

        // when
        Claim claim = dto.createEntity();

        // then
        assertNotNull(claim);
        assertEquals(dto.getDateOfIncident(), claim.getDateOfIncident());
        assertEquals(dto.getDescription(), claim.getDescription());
        assertEquals(dto.getClaimStatus(), claim.getClaimStatus());
        assertEquals(dto.getIdInsurance(), claim.getInsurance().getId());
        assertArrayEquals(dto.getFileContent(), claim.getFileContent());
        assertArrayEquals(dto.getImageContent(), claim.getImageContent());
    }

    @Test
    public void testDtoConstructorFromClaimEntity() {
        // given
        Claim claim = new Claim();
        claim.setId(1L);
        claim.setDateOfIncident(new Date());
        claim.setDescription("Test description");
        claim.setClaimStatus(ClaimStatus.DENIED);
        Insurance insurance = new Insurance();
        insurance.setId(2L);
        claim.setInsurance(insurance);
        claim.setFileContent(new byte[]{1, 2, 3, 4});
        claim.setImageContent(new byte[]{5, 6, 7, 8});

        // when
        ClaimDto dto = new ClaimDto(claim);

        // then
        assertNotNull(dto);
        assertEquals(claim.getId(), dto.getId());
        assertEquals(claim.getDateOfIncident(), dto.getDateOfIncident());
        assertEquals(claim.getDescription(), dto.getDescription());
        assertEquals(claim.getClaimStatus(), dto.getClaimStatus());
        assertEquals(claim.getInsurance().getId(), dto.getIdInsurance());
        assertArrayEquals(claim.getFileContent(), dto.getFileContent());
        assertArrayEquals(claim.getImageContent(), dto.getImageContent());
    }
}