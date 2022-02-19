package com.webpage.predictpoliticalpartyprice.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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


}
