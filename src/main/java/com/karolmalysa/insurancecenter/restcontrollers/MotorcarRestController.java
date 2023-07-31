package com.karolmalysa.insurancecenter.restcontrollers;

import com.karolmalysa.insurancecenter.model.components.MotorcarComponnent;
import com.karolmalysa.insurancecenter.model.dto.MotorcarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/motorcars")
@RequiredArgsConstructor
public class MotorcarRestController {

    private final MotorcarComponnent motorcarComponnent;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public MotorcarDto saveMotorcar(@RequestBody MotorcarDto motorcarDto) {
        return motorcarComponnent.saveMotorcar(motorcarDto);
    }


    @GetMapping(value = "all", produces = "application/json")
    public List<MotorcarDto> findAllMotorcars() {
        return  motorcarComponnent.findAll();
    }

    @GetMapping(produces = "application/json")
    public List<MotorcarDto> findAllMotorcarsWithPagination(@PathParam("page") Integer pageNumber, @PathParam("size") Integer pageSize) {

        return  motorcarComponnent.findAll(pageNumber, pageSize);
    }

    @GetMapping(value = "/by-year/{minYear}/{maxYear}", produces = "application/json")
    public List<MotorcarDto> findMotorcarsByYearBetween(@PathVariable("minYear") Long minYear, @PathVariable ("maxYear") Long maxYear) {
        return  motorcarComponnent.findByYearBetween(minYear, maxYear );
    }


}
