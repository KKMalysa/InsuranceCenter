package com.karolmalysa.insurancecenter.restcontrollers;

import com.karolmalysa.insurancecenter.model.components.ClaimComponnent;
import com.karolmalysa.insurancecenter.model.dto.ClaimDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


}
