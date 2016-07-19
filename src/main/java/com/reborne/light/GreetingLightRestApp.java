package com.reborne.light;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class GreetingLightRestApp 
{
    public static void main( String[] args ) throws Exception {
        SpringApplication.run(GreetingLightRestApp.class, args);
    }
}
