package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.mapper.ContractLogRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Gets the contract logs of the last 7 days of the given date grouped in day intervals
 */
@Repository(value="weekdao")
public class ContractLogWeekDao implements ContractLogDao {

    @Autowired
    ContractLogRowMapper contractLogRowMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ContractLog> getListOfContractLogs(LocalDate date, String Label) {

        final String sqlStatement = "" +
                "SELECT AVG(\"contract_log\".\"last_trade_price\"), DATE((\"contract_log\".\"time_stamp\" at time zone  'EST') at time zone 'Europe/Berlin')  AS day " +
                "FROM contract_log " +
                "INNER JOIN contractdata ON \"contract_log\".\"candidate_id\" = \"contractdata\".\"candidateId\" " +
                "WHERE (\"contractdata\".\"label\" = '" + Label + "') AND  (\"contract_log\".\"time_stamp\" at time zone  'EST') at time zone 'Europe/Berlin'  BETWEEN ('" + date + "'::date- INTERVAL '6 days') AND ('" + date + "'::date+ INTERVAL '1 day')" +
                "GROUP BY day";

        return jdbcTemplate.query(sqlStatement, this.contractLogRowMapper);
    }
}
