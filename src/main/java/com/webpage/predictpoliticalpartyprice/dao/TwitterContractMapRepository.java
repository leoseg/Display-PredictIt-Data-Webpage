package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.TwitterContractMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for table which entries are mapping names of contract to corresponding hashtags for tweetcounts
 */
@Repository
public interface TwitterContractMapRepository extends JpaRepository<TwitterContractMap,Long> {
    /**
     * Gets twittercontractmap entry by name
     * @param name name
     * @return twittercontractmap object
     */
    TwitterContractMap findTwitterContractMapByName(String name);
}
