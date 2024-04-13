package net.skhu.tastyinventory_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;


@EnableJpaAuditing
@SpringBootApplication
public class TastyInventoryBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TastyInventoryBeApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
