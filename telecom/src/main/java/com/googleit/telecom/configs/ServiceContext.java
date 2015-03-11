package com.googleit.telecom.configs;

import com.googleit.telecom.dao.ServiceDAO;
import com.googleit.telecom.dao.ServiceRepository;
import com.googleit.telecom.models.items.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceContext {
    private static final Logger logger = LoggerFactory.getLogger(ServiceContext.class);


    @Bean
    public ServiceDAO serviceDAO() {
        ServiceDAO serviceDAO = new ServiceRepository();

        /* Create sample services */
        Service service = new Service("Service01", "Software Engineering", 100);
        serviceDAO.createService(service);

        service = new Service("Service02", "Design Patter", 200);
        serviceDAO.createService(service);

        service = new Service("Service03", "Open-Closed Principle", 300);
        serviceDAO.createService(service);

        service = new Service("Service04", "GoogleIt", 0);
        serviceDAO.createService(service);

        return serviceDAO;
    }
}
