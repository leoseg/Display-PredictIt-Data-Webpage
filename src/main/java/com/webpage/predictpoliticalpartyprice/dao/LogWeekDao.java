package com.webpage.predictpoliticalpartyprice.dao;

import com.webpage.predictpoliticalpartyprice.entities.ContractLog;
import com.webpage.predictpoliticalpartyprice.entities.TwitterHashtagCountLog;


import java.time.LocalDate;
import java.util.List;

public interface LogWeekDao {
    /**
     * Gets list of contractlogs with same label grouped by day with their average latestTradePrice for the last 7 days
     * @param date date to use
     * @param label only contractlogs with has the samel label for the attribute are choosen
     * @param attribute choose contracty by this attribute
     * @return list of contractlog objects
     */
    List<ContractLog> getListOfContractLogs(LocalDate date, String label,String attribute);

    /**
     * Gets list of twitterhashtagcount logs with same label grouped by day  with their summed up tweetcount for last 7 days
     * @param date date to use
     * @param label only logs with has the samel label are choosen
     * @return list of twitterHashtagCountLogs objects
     */
    List<TwitterHashtagCountLog> getListOfTwitterHashtagCountLogs(LocalDate date, String label);


}
