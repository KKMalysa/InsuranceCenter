package com.karolmalysa.insurancecenter.model.dao;

import com.karolmalysa.insurancecenter.model.entities.Motorcar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorcarRepository extends JpaRepository<Motorcar, Long> {

    List<Motorcar> findByYearBetween(float minYear, float maxYear);
}
