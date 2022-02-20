package com.webpage.predictpoliticalpartyprice.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * ContractLog oject represents the average of contractdata over the given timestamp with the same label
 * a Contract is a bet on a presidentcandidate
 */
public class ContractLog {


    @Setter
    @Getter
    LocalDateTime timestamp;


    @Setter
    @Getter
    double lastTradePrice;

    public ContractLog(){}

    public ContractLog(Double lastTradePrice, LocalDateTime timestamp){
        setLastTradePrice(lastTradePrice);
        setTimestamp(timestamp);
    }

}
