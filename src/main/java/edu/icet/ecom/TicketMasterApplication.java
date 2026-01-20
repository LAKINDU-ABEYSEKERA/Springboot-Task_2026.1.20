package edu.icet.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TicketMasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketMasterApplication.class, args);
    }
}