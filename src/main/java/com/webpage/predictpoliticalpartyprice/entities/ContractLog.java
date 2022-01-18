package com.webpage.predictpoliticalpartyprice.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jdbc.core.convert.Jsr310TimestampBasedConverters;

import java.time.Instant;

public class ContractLog {

    @Getter
    @Setter
    Instant timestamp;

    @Getter
    @Setter
    Double tradePrice;

    public ContractLog(){}

    public ContractLog(Double tradePrice, Instant timestamp){
        setTradePrice(tradePrice);
        setTimestamp(timestamp);
    }

}
