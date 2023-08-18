package com.karolmalysa.insurancecenter.model.dao;

import com.karolmalysa.insurancecenter.model.entities.CompanyClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyClientRepository extends JpaRepository<CompanyClient, Long> {

    CompanyClient findByEmail(String email);
//    Optional<CompanyClient> findById(Long id);


}
