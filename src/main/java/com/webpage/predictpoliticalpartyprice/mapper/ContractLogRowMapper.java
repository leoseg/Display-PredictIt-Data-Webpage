package com.webpage.predictpoliticalpartyprice.mapper;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class ContractLogRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContractLog contractLog = new ContractLog();
        contractLog.setTimestamp(rs.getTimestamp(2));
        contractLog.setTradePrice(rs.getDouble(1));
        return contractLog;
    }
}
