package com.karolmalysa.insurancecenter.restcontrollers;


import com.karolmalysa.insurancecenter.model.components.InsuranceComponnent;
import com.karolmalysa.insurancecenter.model.dto.InsuranceDto;
import com.karolmalysa.insurancecenter.model.entities.Insurance;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/insurances")
@RequiredArgsConstructor
public class InsuranceRestController {

    private  final InsuranceComponnent insuranceComponnent;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public InsuranceDto saveInsurance(@RequestBody InsuranceDto insuranceDto){
        return insuranceComponnent.saveInsurance(insuranceDto);

    }

    @GetMapping(value = "all", produces = "application/json")
    public List<InsuranceDto> findAllInsurances() {
        return  insuranceComponnent.findAll();
    }

    @GetMapping(produces = "application/json")
    public List<InsuranceDto> findAllInsurancesWithPagination(@PathParam("page") Integer pageNumber, @PathParam("size") Integer pageSize) {

        return  insuranceComponnent.findAll(pageNumber, pageSize);
    }

    @GetMapping(value = "/by-price/{minPrice}/{maxPrice}", produces = "application/json")
    public List<InsuranceDto> findInsurancesByPriceBetween(@PathVariable("minPrice") Long minPrice, @PathVariable ("maxPrice") Long maxPrice) {
        return  insuranceComponnent.findByPriceBetween(minPrice, maxPrice );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInsurance(@PathVariable("id") Long id){
        return new ResponseEntity<String>(Insurance.deleteInsurance(id), HttpStatus.OK);
    }
}
