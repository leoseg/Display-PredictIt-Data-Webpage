package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.TwitterContractMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterContractMapRepository extends JpaRepository<TwitterContractMap,Long> {

    TwitterContractMap findTwitterContractMapByName(String name);
}
