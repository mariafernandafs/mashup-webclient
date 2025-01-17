# Desafio MashUP WebClient
Este projeto é um exemplo de como combinar duas APIs distintas (uma para preços e outra para produtos) utilizando Spring WebClient. O objetivo é demonstrar como um consumidor pode usar WebClient para obter dados de duas fontes diferentes e combiná-los em uma resposta única para o cliente.
Descrição do Projeto

O projeto consiste em uma aplicação Spring Boot que consome duas APIs:

    API de Produtos: Retorna informações sobre produtos.
    API de Preços: Retorna informações sobre os preços dos produtos.

![image](https://github.com/user-attachments/assets/cca34f3c-64e4-46a3-8ad7-a4ad99057ffd)
![image](https://github.com/user-attachments/assets/57c0639a-5df6-447b-9d21-24ba7b680dfb)
![image](https://github.com/user-attachments/assets/d89f6a68-5afb-45f1-9fe7-22251dd06c3a)


A aplicação utiliza dois beans do WebClient configurados para se comunicar com cada uma das APIs. As requisições são realizadas de forma assíncrona e os resultados são combinados usando Mono.zip() para formar a resposta final. A aplicação usa o método block() para esperar as requisições e retornar os dados combinados.
Estrutura do Projeto
# 1. Configuração do WebClient
```java
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webclientProdutos(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8081")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient webclientPrecos(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8082")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
```
# 2. Serviço de Preço e Produto
```java
@Service
public class PrecoProdutoService {
    @Autowired
    @Qualifier("webclientProdutos")
    private WebClient webclientProdutos;

    @Autowired
    @Qualifier("webclientPrecos")
    private WebClient webclientPrecos;

    public ProdutoComPreco obterPorCodigo(Long codigoProduto) {
        Mono<Produto> produtoMono = this.webclientProdutos
                .method(HttpMethod.GET)
                .uri("/produtos/{codigo}", codigoProduto)
                .retrieve()
                .bodyToMono(Produto.class);

        Mono<Preco> precoMono = this.webclientPrecos
                .method(HttpMethod.GET)
                .uri("/precos/{codigo}", codigoProduto)
                .retrieve()
                .bodyToMono(Preco.class);

        Mono<ProdutoComPreco> produtoComPrecoMono = Mono.zip(produtoMono, precoMono)
                .map(tuple -> new ProdutoComPreco(tuple.getT1(), tuple.getT2()));

        return produtoComPrecoMono.block();
    }
}
```
 
