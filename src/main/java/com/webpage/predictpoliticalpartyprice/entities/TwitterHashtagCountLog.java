package com.webpage.predictpoliticalpartyprice.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Object that contains tweetcount for given timestamp and totaltweetcount for last 7 days
 */
public class TwitterHashtagCountLog {


    @Getter
    @Setter
    LocalDateTime timestamp;

    @Getter
    @Setter
    int totalTweetCount;

    @Getter
    @Setter
    int tweetCount;

    public TwitterHashtagCountLog(LocalDateTime timestamp,int totalTweetCount, int tweetCount){
        setTweetCount(tweetCount);
        setTimestamp(timestamp);
        setTotalTweetCount(totalTweetCount);
    }

    public  TwitterHashtagCountLog(){}


}
