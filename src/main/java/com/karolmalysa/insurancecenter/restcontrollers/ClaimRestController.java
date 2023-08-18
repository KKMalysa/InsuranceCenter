package com.karolmalysa.insurancecenter.restcontrollers;

import com.karolmalysa.insurancecenter.model.components.ClaimComponnent;
import com.karolmalysa.insurancecenter.model.dto.ClaimDto;
import com.karolmalysa.insurancecenter.model.entities.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/claims")
@RequiredArgsConstructor
public class ClaimRestController {


    private final ClaimComponnent claimComponnent;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ClaimDto saveClaim(@RequestBody ClaimDto claimDto) {
        return claimComponnent.saveClaim(claimDto);
    }

    @GetMapping(value = "all", produces = "application/json")
    public List<ClaimDto> findAllClaims() {
        return  claimComponnent.findAll();
    }

    @GetMapping(produces = "application/json")
    public List<ClaimDto> findAllClaimsWithPagination(@PathParam("page") Integer pageNumber, @PathParam("size") Integer pageSize) {

        return  claimComponnent.findAll(pageNumber, pageSize);
    }

    /** ------------------------------------------------------------------------------------- */
    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable("id") Long id){
        return new ResponseEntity<Claim>(claimComponnent.getClaimById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClaimById(@PathVariable("id") Long id){
        return new ResponseEntity<String>(claimComponnent.deleteClaim(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(@Valid @RequestBody Claim claim, @PathVariable("id") Long id){
        return new ResponseEntity<Claim>(claimComponnent.updateClaim(claim, id),HttpStatus.OK);
    }
}
