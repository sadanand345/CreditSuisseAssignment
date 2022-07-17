package com.creditsuisseassignment.confg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Config {
    @Value("${time-threshold}")
    private int timeThreshold;

    @Value("${data-structure-threshold}")
    private int dataStructureThreshold;
}
