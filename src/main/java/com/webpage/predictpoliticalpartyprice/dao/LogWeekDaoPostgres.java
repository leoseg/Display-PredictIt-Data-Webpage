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
 * Gets the contract logs of the last 7 days of the given date grouped in day intervals from postgres
 */
@Repository(value="weekdao")
public class LogWeekDaoPostgres implements LogWeekDao {

    @Resource
    ContractLogRowMapper contractLogRowMapper;

    @Resource
    TwitterHashtagCountLogRowMapper twitterHashtagCountLogRowMapper;

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    @Qualifier("attributeColumNameMap")
    Map<String,String> attributeColumNameMap;



    @Override
    public List<ContractLog> getListOfContractLogs(LocalDate date, String label, String attribute) {
        String columName = attributeColumNameMap.get(attribute);
        final String sqlStatement = "" +
                "SELECT AVG(\"contract_log\".\"last_trade_price\"), DATE(\"contract_log\".\"time_stamp\" at time zone  'EST') AS day " +
                "FROM contract_log " +
                "INNER JOIN contractdata ON \"contract_log\".\"candidate_id\" = \"contractdata\".candidateid " +
                "WHERE ("+String.format("contractdata.%s",columName)+"= '" +label + "') AND  (\"contract_log\".\"time_stamp\" at time zone  'EST')   BETWEEN ('" + date + "'::date- INTERVAL '6 days') AND ('" + date + "'::date+ INTERVAL '1 day')" +
                "GROUP BY day";

        return jdbcTemplate.query(sqlStatement, this.contractLogRowMapper);
    }

    @Override
    public List<TwitterHashtagCountLog> getListOfTwitterHashtagCountLogs(LocalDate date, String label) {
        final String sqlStatement = "" +
                "SELECT  DATE(\"twitter_hashtag_count_log\".\"time_stamp\"at time zone 'UTC')  AS day , SUM(\"twitter_hashtag_count_log\".\"tweet_count\"), AVG(\"twitter_hashtag_count_log\".\"total_tweet_count\")  " +
                "FROM twitter_hashtag_count_log " +
                "INNER JOIN twittercontractmap ON \"twitter_hashtag_count_log\".\"name\" = \"twittercontractmap\".\"topicname\" " +
                "WHERE \"twittercontractmap\".\"name\" = '"+label + "' AND  (\"twitter_hashtag_count_log\".\"time_stamp\" at time zone 'UTC')   BETWEEN ('" + date + "'::date- INTERVAL '6 days') AND ('" + date + "'::date+ INTERVAL '1 day')" +
                "GROUP BY day";

        return jdbcTemplate.query(sqlStatement, this.twitterHashtagCountLogRowMapper);
    }


}
