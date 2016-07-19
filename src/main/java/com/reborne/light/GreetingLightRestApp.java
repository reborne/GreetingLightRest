package com.reborne.light;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
public class GreetingLightRestApp 
{
    public static void main( String[] args ) throws Exception {
        SpringApplication.run(GreetingLightRestApp.class, args);
    }
}
