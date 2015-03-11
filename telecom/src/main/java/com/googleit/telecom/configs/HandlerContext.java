package com.googleit.telecom.configs;

import com.googleit.telecom.services.CustomerHandler;
import com.googleit.telecom.services.CustomerRepHandler;
import com.googleit.telecom.services.MarketingRepHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerContext {
    private static final Logger logger = LoggerFactory.getLogger(HandlerContext.class);

    @Bean
    public CustomerHandler customerHandler() {
        logger.info("CustomerHandler Bean");
        return new CustomerHandler();
    }

    @Bean
    public CustomerRepHandler customerRepHandler() {
        logger.info("CustomerHandler Bean");
        return new CustomerRepHandler();
    }

    @Bean
    public MarketingRepHandler marketingRepHandler() {
        logger.info("CustomerHandler Bean");
        return new MarketingRepHandler();
    }

}
