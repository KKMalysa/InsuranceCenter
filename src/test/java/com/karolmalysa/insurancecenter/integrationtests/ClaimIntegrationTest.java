/** @TODO do poprawy */

//package com.karolmalysa.insurancecenter.integrationtests;
//
//import com.karolmalysa.insurancecenter.model.dto.ClaimDto;
//import com.karolmalysa.insurancecenter.model.entities.ClaimStatus;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ClaimIntegrationTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @LocalServerPort
//    private int port;
//
//    private String getRootUrl() {
//        return "http://localhost:" + port;
//    }
//
//    // Test sprawdzający czy można utworzyć nowe zgłoszenie
//    @Test
//    public void testCreateClaim() {
//        ClaimDto claimDto = new ClaimDto();
//        claimDto.setId(1L);  //id
//        claimDto.setDescription("Uszkodzenie samochodu na parkingu");  // Opis zgłoszenia
//        claimDto.setStatus(ClaimStatus.INVESTIGATION);  // Status zgłoszenia
//        claimDto.setAmount(new BigDecimal("5000.00"));  // Kwota zgłoszenia, jako BigDecimal dla precyzyjności
//        claimDto.setDateFiled(LocalDate.now());  // Data zgłoszenia, ustawiona na dzisiaj
//
//        ResponseEntity<ClaimDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/claims", claimDto, ClaimDto.class);
//        assertNotNull(postResponse);
//        assertNotNull(postResponse.getBody());
//    }
//
//    // Test sprawdzający czy można pobrać zgłoszenie po ID
//    @Test
//    public void testGetClaimById() {
//        ClaimDto claimDto = restTemplate.getForObject(getRootUrl() + "/claims/1", ClaimDto.class);
//        assertNotNull(claimDto);
//    }
//
//    // Test sprawdzający czy można zaktualizować zgłoszenie
//    @Test
//    public void testUpdateClaim() {
//        int id = 1;
//        ClaimDto claimDto = restTemplate.getForObject(getRootUrl() + "/claims/" + id, ClaimDto.class);
//        // Zmiana wartości w claimDto...
//        claimDto.setDescription("Nowy opis roszczenia o odszkodowanie z powodu powstałej szkody");
//
//        restTemplate.put(getRootUrl() + "/claims/" + id, claimDto);
//
//        ClaimDto updatedClaim = restTemplate.getForObject(getRootUrl() + "/claims/" + id, ClaimDto.class);
//        assertNotNull(updatedClaim);
//    }
//
//    // Test sprawdzający czy można usunąć zgłoszenie
//    @Test
//    public void testDeleteClaim() {
//        int id = 1;
//        ClaimDto claimDto = restTemplate.getForObject(getRootUrl() + "/claims/" + id, ClaimDto.class);
//        assertNotNull(claimDto);
//
//        restTemplate.delete(getRootUrl() + "/claims/" + id);
//
//        try {
//            claimDto = restTemplate.getForObject(getRootUrl() + "/claims/" + id, ClaimDto.class);
//        } catch (final HttpClientErrorException e) {
//            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
//        }
//    }
//}
//
