package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.mapper.ContractLogRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository(value="daydao")
public class ContractLogDayDao implements ContractLogDao {

    @Autowired
    ContractLogRowMapper contractLogRowMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ContractLog> getListOfContractLogs(Date date, String Label) {

        final String sqlStatement= "" +
                "SELECT AVG(\"contract_log\".\"last_trade_price\"), " +
                "(to_timestamp(round((extract('epoch' from \"contract_log\".\"time_stamp\") / 600)) * 600) at time zone 'Asia/Pontianak') at time zone 'Europe/Berlin' as timestamp "+
                "FROM contract_log " +
                "INNER JOIN contractdata ON \"contract_log\".\"candidate_id\" = \"contractdata\".\"candidateId\"" +
                "WHERE \"contractdata\".\"label\" = '+Label+' AND  DATE((\"contract_log\".\"time_stamp\" at time zone  'Asia/Pontianak') at time zone 'Europe/Berlin') = '"+date+"' "+
                "GROUP BY timestamp";

        List query = jdbcTemplate.query(sqlStatement, this.contractLogRowMapper);
        return query;
    }
}
