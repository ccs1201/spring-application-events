package br.com.ccs.springapplicationevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringApplicationEventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationEventsApplication.class, args);
    }

}
