package com.webpage.predictpoliticalpartyprice.mapper;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * For mapping the resultset to the contractlog object
 */
@Component
public class ContractLogRowMapper implements RowMapper<ContractLog> {
    /**
     * Maps the given result set with the timestamp and tradeprice columns to a contractlog object
     * @param rs resultset of query
     * @param rowNum rowNum of resultset
     * @return contractlog object
     * @throws SQLException exception of sql query
     */
    @Override
    public ContractLog mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContractLog contractLog = new ContractLog();
        contractLog.setTimestamp(rs.getTimestamp(2).toLocalDateTime());//.plus(1,ChronoUnit.HOURS));
        contractLog.setLogvalue(rs.getDouble(1));
        return contractLog;
    }
}
