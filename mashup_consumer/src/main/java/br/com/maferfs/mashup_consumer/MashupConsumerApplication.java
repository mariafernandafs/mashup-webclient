package br.com.maferfs.mashup_consumer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MashupConsumerApplication {

	@Bean
	@Qualifier("webclientProdutos")
	public WebClient webclientProdutos(WebClient.Builder builder){
		return builder
				.baseUrl("http://localhost:8081")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
	@Bean
	@Qualifier("webclientPrecos")
	public WebClient webclientPrecos(WebClient.Builder builder){
		return builder
				.baseUrl("http://localhost:8082")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MashupConsumerApplication.class, args);
	}

}
