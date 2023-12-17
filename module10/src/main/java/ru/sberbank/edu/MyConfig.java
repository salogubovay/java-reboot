package ru.sberbank.edu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class MyConfig {
   
    @Bean
    public FinancialService financialService() {
        FinancialService financialService = new FinancialService();
        return financialService;
    }

}
