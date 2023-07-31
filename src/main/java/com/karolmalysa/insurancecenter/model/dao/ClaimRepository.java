package com.karolmalysa.insurancecenter.model.dao;

import com.karolmalysa.insurancecenter.model.entities.Claim;
import com.karolmalysa.insurancecenter.model.entities.Motorcar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {


}
