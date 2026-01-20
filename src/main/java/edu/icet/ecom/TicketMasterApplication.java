package edu.icet.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
public class TicketMasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketMasterApplication.class, args);
    }
}