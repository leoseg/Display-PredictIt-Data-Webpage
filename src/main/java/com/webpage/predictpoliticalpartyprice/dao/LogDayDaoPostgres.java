package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.entities.TwitterHashtagCountLog;
import com.webpage.predictpoliticalpartyprice.mapper.ContractLogRowMapper;
import com.webpage.predictpoliticalpartyprice.mapper.TwitterHashtagCountLogRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Gets the contracts log of the given date grouped in 10 minute intervals from postgres
 */
@Repository(value="daydao")
public class LogDayDaoPostgres implements LogDayDao {

    @Resource
    ContractLogRowMapper contractLogRowMapper;

    @Resource
    TwitterHashtagCountLogRowMapper twitterHashtagCountLogRowMapper;

    @Resource
    @Qualifier("attributeColumNameMap")
    Map<String,String> attributeColumNameMap;

    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ContractLog> getListOfContractLogs(LocalDate date, String label,String attribute) {

        String columName = attributeColumNameMap.get(attribute);
        final String sqlStatement= "" +
                "SELECT AVG(\"contract_log\".\"last_trade_price\"), " +
                "(to_timestamp(round((extract('epoch' from (\"contract_log\".\"time_stamp\"  at time zone 'EST') )/ 600)) * 600)) as timestamp "+
                "FROM contract_log " +
                "INNER JOIN contractdata ON \"contract_log\".\"candidate_id\" = \"contractdata\".candidateid " +
                "WHERE ("+String.format("contractdata.%s",columName)+"= '" +label + "') AND  DATE(\"contract_log\".\"time_stamp\" at time zone  'EST')  = '"+date+"' "+
                "GROUP BY timestamp";

        return jdbcTemplate.query(sqlStatement, this.contractLogRowMapper);

    }

    @Override
    public List<TwitterHashtagCountLog> getListOfTwitterHashtagCountLogs(LocalDate date, String label) {

        final String sqlStatement = "" +
                "SELECT  (to_timestamp(round(extract('epoch' from (\"twitter_hashtag_count_log\".\"time_stamp\" at time zone 'UTC') )/ 3600) * 3600)) as timestamp , "+
                "SUM(\"twitter_hashtag_count_log\".\"tweet_count\"), AVG(\"twitter_hashtag_count_log\".\"total_tweet_count\") " +
                "FROM twitter_hashtag_count_log " +
                "INNER JOIN twittercontractmap ON \"twitter_hashtag_count_log\".\"name\" = \"twittercontractmap\".\"topicname\" " +
                "WHERE \"twittercontractmap\".\"name\" = '"+label + "' AND  DATE(\"twitter_hashtag_count_log\".\"time_stamp\" at time zone 'UTC') =  '"+date+"' "+
                "GROUP BY timestamp";
        return jdbcTemplate.query(sqlStatement, this.twitterHashtagCountLogRowMapper);
    }
}
