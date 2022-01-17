package com.webpage.predictpoliticalpartyprice.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jdbc.core.convert.Jsr310TimestampBasedConverters;

import java.sql.Timestamp;

public class ContractLog {

    @Getter
    @Setter
    Timestamp timestamp;

    @Getter
    @Setter
    Double tradePrice;

    public ContractLog(){}

    public ContractLog(Double tradePrice, Timestamp timestamp){
        setTradePrice(tradePrice);
        setTimestamp(timestamp);
    }

}
