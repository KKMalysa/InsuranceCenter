package com.karolmalysa.insurancecenter.restcontrollers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClaimRestControllerE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testFindAllClaims() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth("KKM", "79988");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            // Wykonuje żądanie
            ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:" + port + "/claims/all", List.class);

            //w odpowiedzi leci html zamiast jsona. albo to przez logowanie samo w sobie, albo przez bcrypt i NoOpPasswordEncoder używane na raz
            System.out.println("Odpowiedź: " + response.getBody());
            System.out.println("Nagłówki: " + response.getHeaders());

            // Sprawdzam status i treść odpowiedzi
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getHeaders().getContentType().toString()).contains("application/json"); // Sprawdzenie typu MIME
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().size()).isGreaterThan(0);  // lub inne asercje
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}