package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.ContractData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface ContractDataRepository extends JpaRepository<ContractData, Integer> {

    @Query("SELECT cd FROM ContractData cd")
    List<ContractData> getContractDataOfPresidentsElections();
}
