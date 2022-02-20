package com.webpage.predictpoliticalpartyprice.mapper;

import com.webpage.predictpoliticalpartyprice.entities.TwitterHashtagCountLog;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapps resultset to twitterhashtagcountlog object
 */
@Component
public class TwitterHashtagCountLogRowMapper implements RowMapper<TwitterHashtagCountLog> {


    /**
     * Maps the timestamp, tweetcount and totaltweetcount from resultset to log object
     * @param rs resultset from query
     * @param rowNum number of row
     * @return twitterhashtagcountlog object
     * @throws SQLException thrown by resultset
     */
    @Override
    public TwitterHashtagCountLog mapRow(ResultSet rs, int rowNum) throws SQLException {
        TwitterHashtagCountLog twitterHashtagCountLog = new TwitterHashtagCountLog();
        twitterHashtagCountLog.setTimestamp(rs.getTimestamp(1).toLocalDateTime());
        twitterHashtagCountLog.setTweetCount(rs.getInt(2));
        twitterHashtagCountLog.setTotalTweetCount(rs.getInt(3));
        return twitterHashtagCountLog;
    }
}
