package br.com.nullbakcopy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class NullbakcopyApplication {

	public static void main(String[] args) {
		SpringApplication.run(NullbakcopyApplication.class, args);
		System.out.println("deu certo");
	}
}
