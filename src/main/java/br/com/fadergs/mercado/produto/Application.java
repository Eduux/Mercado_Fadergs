package br.com.fadergs.mercado.produto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("br.com.fadergs.mercado.produto.persistence.repo") 
@EntityScan("br.com.fadergs.mercado.produto.persistence.model")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
    }
}
