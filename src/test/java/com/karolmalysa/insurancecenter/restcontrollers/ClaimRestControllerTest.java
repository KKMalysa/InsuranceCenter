package com.karolmalysa.insurancecenter.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karolmalysa.insurancecenter.model.components.ClaimComponnent;
import com.karolmalysa.insurancecenter.model.dto.ClaimDto;
import com.karolmalysa.insurancecenter.model.entities.Claim;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ClaimRestControllerTest {

    @Mock
    private ClaimComponnent claimComponnent;

    @InjectMocks
    private ClaimRestController claimRestController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(claimRestController).build();
    }

    @Test
    public void testFindAllClaims() throws Exception {
        //given
        ClaimDto mockClaimDto = new ClaimDto();
        when(claimComponnent.findAll()).thenReturn(Collections.singletonList(mockClaimDto));

        //when & then
        mockMvc.perform(get("/claims/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]")); // Uproszczony JSON, który reprezentuje jedno zgłoszenie
    }

    @Test
    public void testGetClaimById() throws Exception {
        // given
        Claim mockClaim = new Claim();
        when(claimComponnent.getClaimById(1L)).thenReturn(mockClaim);

        //when & then
        mockMvc.perform(get("/claims/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClaimByIdNotFound() throws Exception {
        //given
        when(claimComponnent.getClaimById(1L)).thenReturn(null);

        //when & then
        mockMvc.perform(get("/claims/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveClaim() throws Exception {
        // given
        ClaimDto claimDtoToSave = new ClaimDto();
        ClaimDto savedClaimDto = new ClaimDto(); // zakładając, że ClaimDto ma jakieś pole do identyfikacji
        when(claimComponnent.saveClaim(claimDtoToSave)).thenReturn(savedClaimDto);

        // when & then
        mockMvc.perform(post("/claims")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(claimDtoToSave)))
                .andExpect(status().isOk())
                .andExpect(content().json("{}")); // Uproszczony JSON dla zapisanego zgłoszenia
    }

    @Test
    public void testDeleteClaimById() throws Exception {
        // given
        Long idToDelete = 1L;
        when(claimComponnent.deleteClaim(idToDelete)).thenReturn("Deleted");

        // when & then
        mockMvc.perform(delete("/claims/" + idToDelete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }

    @Test
    public void testUpdateClaim() throws Exception {
        // given
        Long idToUpdate = 1L;
        Claim claimToUpdate = new Claim();
        Claim updatedClaim = new Claim(); // zakładając, że Claim ma jakieś pole do identyfikacji
        when(claimComponnent.updateClaim(claimToUpdate, idToUpdate)).thenReturn(updatedClaim);

        // when & then
        mockMvc.perform(put("/claims/" + idToUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(claimToUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));  // Uproszczony JSON dla zaktualizowanego zgłoszenia
    }


}

