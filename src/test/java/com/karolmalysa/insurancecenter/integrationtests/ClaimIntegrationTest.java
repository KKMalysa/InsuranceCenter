package com.karolmalysa.insurancecenter.integrationtests;

import com.karolmalysa.insurancecenter.model.dto.ClaimDto;
import com.karolmalysa.insurancecenter.model.entities.ClaimStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClaimIntegrationTest {

    @BeforeEach
    public void setup() {
        this.restTemplate = this.restTemplate.withBasicAuth("KKM", "79988");
    }


    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;



    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    // Test sprawdzający czy można utworzyć nowe zgłoszenie
    @Test
    public void testCreateClaim() {

        ClaimDto claimDto = new ClaimDto();
        claimDto.setId(1L);  //id
        claimDto.setDescription("Uszkodzenie samochodu na parkingu");
        claimDto.setStatus(ClaimStatus.INVESTIGATION);
        claimDto.setAmount(new BigDecimal("5000.00"));
        claimDto.setDateOfIncident(new Date());
        claimDto.setFileContent(new byte[]{ /* ... */ });
        claimDto.setImageContent(new byte[]{ /* ... */ });
        claimDto.setIdInsurance(123L);

        ResponseEntity<ClaimDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/claims", claimDto, ClaimDto.class);
        assertNotNull(postResponse);
    }

    // Test sprawdzający czy można pobrać zgłoszenie po ID
    @Test
    public void testGetClaimById() {
        ClaimDto claimDto = restTemplate.getForObject(getRootUrl() + "/claims/1", ClaimDto.class);
        assertNotNull(claimDto);
    }

    // Test sprawdzający czy można zaktualizować zgłoszenie
    @Test
    public void testUpdateClaim() {

        int id = 1;
        ClaimDto claimDto = restTemplate.getForObject(getRootUrl() + "/claims/" + id, ClaimDto.class);
        // Zmiana wartości w claimDto...
        claimDto.setDescription("Nowy opis roszczenia o odszkodowanie z powodu powstałej szkody");

        restTemplate.put(getRootUrl() + "/claims/" + id, claimDto);

        ClaimDto updatedClaim = restTemplate.getForObject(getRootUrl() + "/claims/" + id, ClaimDto.class);
        assertNotNull(updatedClaim);
    }

    // Test sprawdzający czy można usunąć zgłoszenie
    @Test
    public void testDeleteClaim() {
        int id = 1;
        ClaimDto claimDto = restTemplate.getForObject(getRootUrl() + "/claims/" + id, ClaimDto.class);
        assertNotNull(claimDto);

        restTemplate.delete(getRootUrl() + "/claims/" + id);

        claimDto = null;

        try {
            claimDto = restTemplate.getForObject(getRootUrl() + "/claims/" + id, ClaimDto.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}

