package br.edu.utfpr.td.tsi.trabalho1apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("br.edu.utfpr.td.tsi.trabalho1apis.repository")
public class Trabalho1apisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Trabalho1apisApplication.class, args);
    }

}
