package com.karolmalysa.insurancecenter.model.components;

import com.karolmalysa.insurancecenter.exception.ResourceNotFoundException;
import com.karolmalysa.insurancecenter.model.dao.MotorcarRepository;
import com.karolmalysa.insurancecenter.model.dto.MotorcarDto;
import com.karolmalysa.insurancecenter.model.entities.Motorcar;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MotorcarComponnent {

    private static MotorcarRepository motorcarRepository;

    public MotorcarDto saveMotorcar (MotorcarDto motorcarDto) {
        Motorcar motorcar = motorcarRepository.save(motorcarDto.createEntity());

        return new MotorcarDto(motorcar);
    }
    public static String deleteMotorcar(Long id) {
        Motorcar motorcar = motorcarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Motorcar", "motorcarId", id.toString()));
        motorcarRepository.delete(motorcar);

        return "Motorcar data has been deleted successfully...";
    }

    public List<MotorcarDto> findAll () {
        return motorcarRepository.findAll().stream()
                .map(motorcar -> new MotorcarDto(motorcar))
                .collect(Collectors.toList());

    }

    /** pagination */
    public List<MotorcarDto> findAll (Integer pageNumber, Integer pageSize) {
        return motorcarRepository.findAll(PageRequest.of(pageNumber, pageSize)).stream()
                .map(motorcar -> new MotorcarDto(motorcar))
                .collect(Collectors.toList());
    }


    public List<MotorcarDto> findByYearBetween (Long minYear, Long maxYear ) {
        return motorcarRepository.findByYearBetween(minYear.floatValue(),maxYear.floatValue() ).stream()
                .map(motorcar -> new MotorcarDto(motorcar))
                .collect(Collectors.toList());


    }

}
