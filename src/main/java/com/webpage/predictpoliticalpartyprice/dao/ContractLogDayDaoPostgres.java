package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.mapper.ContractLogRowMapper;
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
public class ContractLogDayDaoPostgres implements ContractLogDayDao {

    @Resource
    ContractLogRowMapper contractLogRowMapper;

    @Resource
    @Qualifier("attributeColumNameMap")
    Map<String,String> attributeColumNameMap;

    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ContractLog> getListOfContractLogs(LocalDate date, String label,String attribute) {

        String columName = attributeColumNameMap.get(attribute);
        final String sqlStatement= "" +
                "SELECT SUM(\"contract_log\".\"last_trade_price\"), " +
                "(to_timestamp(round((extract('epoch' from (\"contract_log\".\"time_stamp\"  at time zone 'EST') )/ 600)) * 600)) as timestamp "+
                "FROM contract_log " +
                "INNER JOIN contractdata ON \"contract_log\".\"candidate_id\" = \"contractdata\".candidateid" +
                " WHERE ("+String.format("contractdata.%s",columName)+"= '" +label + "') AND  DATE(\"contract_log\".\"time_stamp\" at time zone  'EST')  = '"+date+"' "+
                "GROUP BY timestamp";

        return jdbcTemplate.query(sqlStatement, this.contractLogRowMapper);

    }
}
