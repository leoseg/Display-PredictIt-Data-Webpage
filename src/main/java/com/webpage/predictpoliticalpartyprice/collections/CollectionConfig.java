package com.webpage.predictpoliticalpartyprice.collections;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class CollectionConfig {

    /**
     * Maps names of attribut to columns so only the daos gets real colum names
     * @return list with names
     */
    @Bean(value="attributeColumNameMap")
    public Map<String,String> attributeColumNameMap () {
        return Map.of(
                "Name", "name",
                "PoliticalLabel", "label");

    }
}
