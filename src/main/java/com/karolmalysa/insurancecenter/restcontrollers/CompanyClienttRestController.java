package com.karolmalysa.insurancecenter.restcontrollers;


import com.karolmalysa.insurancecenter.model.components.CompanyClientComponnent;
import com.karolmalysa.insurancecenter.model.dto.CompanyClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/companyClient")
@RequiredArgsConstructor
public class CompanyClienttRestController {

    private  final CompanyClientComponnent companyClientComponnent;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public CompanyClientDto saveCompanyClient(@RequestBody CompanyClientDto companyClientDto){
        return companyClientComponnent.saveCompanyClient(companyClientDto);

    }

    @GetMapping(value = "all", produces = "application/json")
    public List<CompanyClientDto> findAllCompanyClients() {
        return companyClientComponnent.findAll();
    }

    @GetMapping(produces = "application/json")
    public List<CompanyClientDto> findAllCompanyClientsWithPagination(@PathParam("page") Integer pageNumber, @PathParam("size") Integer pageSize) {

        return  companyClientComponnent.findAll(pageNumber, pageSize);
    }

    @GetMapping(value = "/multiple-insured", produces = "application/json")
    public List<CompanyClientDto> findMultipleInsuredClients() {

        return  companyClientComponnent.findMultipleInsuredClients();
    }


}
