package com.karolmalysa.insurancecenter.model.components;

import com.karolmalysa.insurancecenter.model.dao.InsuranceRepository;
import com.karolmalysa.insurancecenter.model.dto.InsuranceDto;
import com.karolmalysa.insurancecenter.model.entities.Insurance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class InsuranceComponnentTest {

    @Mock
    private InsuranceRepository insuranceRepository;

    @InjectMocks
    private InsuranceComponnent insuranceComponnent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveInsurance() {
        InsuranceDto insuranceDto = new InsuranceDto();
        insuranceDto.setName("Test Insurance");
        insuranceDto.setStartDate(new Date());
        insuranceDto.setEndDate(new Date());
        insuranceDto.setPrice(500.0f);

        Insurance insurance = insuranceDto.createEntity();
        when(insuranceRepository.save(any(Insurance.class))).thenReturn(insurance);

        InsuranceDto result = insuranceComponnent.saveInsurance(insuranceDto);
        assertEquals(insuranceDto.getName(), result.getName());
    }

}

