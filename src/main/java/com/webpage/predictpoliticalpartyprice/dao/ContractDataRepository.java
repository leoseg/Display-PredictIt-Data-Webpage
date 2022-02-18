package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.ContractData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ContractDataRepository extends JpaRepository<ContractData, Integer> {
}
