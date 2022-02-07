package com.webpage.predictpoliticalpartyprice.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jdbc.core.convert.Jsr310TimestampBasedConverters;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * ContractLog oject represents the average of contractdata over the given timestamp with the same label
 */
public class ContractLog {

    @Getter
    @Setter
    LocalDateTime timestamp;

    @Getter
    @Setter
    Double tradePrice;

    public ContractLog(){}

    public ContractLog(Double tradePrice, LocalDateTime timestamp){
        setTradePrice(tradePrice);
        setTimestamp(timestamp);
    }

}
