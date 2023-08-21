package com.karolmalysa.insurancecenter.model.entities;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClaimTest {

    @Test
    public void testId() {
        // given
        Claim claim = new Claim();
        Long expectedId = 1L;

        // when
        claim.setId(expectedId);

        // then
        assertEquals(expectedId, claim.getId());
    }

    @Test
    public void testDateOfIncident() {
        // given
        Claim claim = new Claim();
        Date date = new Date();

        // when
        claim.setDateOfIncident(date);

        // then
        assertEquals(date, claim.getDateOfIncident());
    }

    @Test
    public void testDescription() {
        // given
        Claim claim = new Claim();
        String description = "Sample description";

        // when
        claim.setDescription(description);

        // then
        assertEquals(description, claim.getDescription());
    }

    @Test
    public void testClaimStatus() {
        // given
        Claim claim = new Claim();
        ClaimStatus status = ClaimStatus.SUBMITTED;

        // when
        claim.setClaimStatus(status);

        // then
        assertEquals(status, claim.getClaimStatus());
    }

    @Test
    public void testFileContent() {
        // given
        Claim claim = new Claim();
        byte[] content = new byte[]{1, 2, 3, 4};

        // when
        claim.setFileContent(content);

        // then
        assertArrayEquals(content, claim.getFileContent());
    }

    @Test
    public void testImageContent() {
        // given
        Claim claim = new Claim();
        byte[] content = new byte[]{1, 2, 3, 4};

        // when
        claim.setImageContent(content);

        // then
        assertArrayEquals(content, claim.getImageContent());
    }

    @Test
    public void testInsurance() {
        // given
        Claim claim = new Claim();
        Insurance insurance = new Insurance();

        // when
        claim.setInsurance(insurance);

        // then
        assertEquals(insurance, claim.getInsurance());
    }
}