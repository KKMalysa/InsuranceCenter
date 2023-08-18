package com.karolmalysa.insurancecenter.restcontrollers;


import com.karolmalysa.insurancecenter.model.components.CompanyClientComponnent;
import com.karolmalysa.insurancecenter.model.dto.CompanyClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/companyClient")
@RequiredArgsConstructor
public class CompanyClientRestController {

    @Autowired
    private  final CompanyClientComponnent companyClientComponnent;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public CompanyClientDto saveCompanyClient(@RequestBody CompanyClientDto companyClientDto){
        return companyClientComponnent.saveCompanyClient(companyClientDto);

    }

	@PostMapping("createClient")
	public ResponseEntity<CompanyClientDto> createClient(@Valid @RequestBody CompanyClientDto companyClient){
	return new ResponseEntity<CompanyClientDto>(companyClientComponnent.addCompanyClient(companyClient),HttpStatus.CREATED);
	}

    @GetMapping(value = "all", produces = "application/json")
    public List<CompanyClientDto> findAllCompanyClients() {
        return companyClientComponnent.findAll();
    }

    @GetMapping(produces = "application/json")
    public List<CompanyClientDto> findAllCompanyClientsWithPagination(@PathParam("page") Integer pageNumber, @PathParam("size") Integer pageSize) {

        return  companyClientComponnent.findAll(pageNumber, pageSize);
    }

    @PutMapping("/update-client/{id}")
    public ResponseEntity<CompanyClientDto> updateClient(@Valid @RequestBody CompanyClientDto client, @PathVariable("id") Long id){
        return new ResponseEntity<CompanyClientDto>(companyClientComponnent.updateClientInfo(client, id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient( @PathVariable("id") Long id){
        return new ResponseEntity<String>(companyClientComponnent.deleteClient(id),HttpStatus.OK);
    }
    @GetMapping(value = "/multiple-insured", produces = "application/json")
    public List<CompanyClientDto> findMultipleInsuredClients() {

        return  companyClientComponnent.findMultipleInsuredClients();
    }
    @GetMapping("/findCompanyClientById/{companyClientId}")
    public ResponseEntity<CompanyClientDto> findCompanyClientById(@PathVariable("companyClientId") Long companyClientId){
        return new ResponseEntity<CompanyClientDto>(companyClientComponnent.findCompanyClientById(companyClientId),HttpStatus.OK);
    }

    @GetMapping("/get-client-by-email/")
    public ResponseEntity<CompanyClientDto> getClientByEmail(@RequestParam("email") String email){
        return new ResponseEntity<CompanyClientDto>(companyClientComponnent.findByEmail(email),HttpStatus.OK);
    }


}
