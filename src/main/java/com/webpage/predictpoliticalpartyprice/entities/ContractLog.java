package com.webpage.predictpoliticalpartyprice.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * ContractLog oject represents the average of contractdata over the given timestamp with the same label
 * a Contract is a bet on a presidentcandidate
 */
public class ContractLog {

    @Getter
    @Setter
    LocalDateTime timestamp;

    @Getter
    @Setter
    Double logvalue;

    public ContractLog(){}

    public ContractLog(Double logvalue, LocalDateTime timestamp){
        setLogvalue(logvalue);
        setTimestamp(timestamp);
    }

}
