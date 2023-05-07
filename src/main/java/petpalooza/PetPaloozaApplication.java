package petpalooza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication

public class PetPaloozaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetPaloozaApplication.class, args);

    }
}
