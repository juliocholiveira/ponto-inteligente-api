package com.jcho.pontointeligente.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jcho.pontointeligente.api.utils.PasswordUtils;

@SpringBootApplication
public class PontoInteligenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PontoInteligenteApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			String senha1 = PasswordUtils.gerarBCrypt("123456");
			System.out.println("Senha 1: " + senha1);
		};
	}
}
