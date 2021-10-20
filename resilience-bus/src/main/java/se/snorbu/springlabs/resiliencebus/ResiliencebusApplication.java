package se.snorbu.springlabs.resiliencebus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ResiliencebusApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResiliencebusApplication.class, args);
    }
}
