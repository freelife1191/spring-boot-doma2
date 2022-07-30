package com.sample;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Kane on 2022/07/29.
 */
@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class MyConfiguration {

}