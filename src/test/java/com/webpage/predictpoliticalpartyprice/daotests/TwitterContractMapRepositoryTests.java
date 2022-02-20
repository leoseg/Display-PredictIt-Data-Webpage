package com.webpage.predictpoliticalpartyprice.daotests;

import com.webpage.predictpoliticalpartyprice.dao.TwitterContractMapRepository;
import com.webpage.predictpoliticalpartyprice.entities.TwitterContractMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TwitterContractMapRepositoryTests {


    @Resource
    TwitterContractMapRepository twitterContractMapRepository;

    @Test
    void givenName_whenRepositoryFindByName_shouldReturnExspected(){
        TwitterContractMap exspectedTwitterContractMap = new TwitterContractMap();
        exspectedTwitterContractMap.setName("testname");
        exspectedTwitterContractMap.setHashtag("#test");
        exspectedTwitterContractMap.setTopicName("testtopic");
        exspectedTwitterContractMap.setTranslation("test translation");
        twitterContractMapRepository.save(exspectedTwitterContractMap);

        assert(twitterContractMapRepository.findTwitterContractMapByName("testname") == exspectedTwitterContractMap);
    }
}
