package br.com.maferfs.mashup_consumer.service;

import br.com.maferfs.mashup_consumer.model.ProdutoComPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PrecoProdutoService {
    @Autowired
    @Qualifier("webclientProdutos")
    private WebClient webclientProdutos;

    @Autowired
    @Qualifier("webclientPrecos")
    private WebClient webclientPrecos;

    public ProdutoComPreco obterPorCodigo(Long codigoProduto){
        Mono<ProdutoComPreco> monoProduto = this.webclientProdutos
                .method(HttpMethod.GET)
                .uri("/produtos/{codigo}", codigoProduto)
                .retrieve()//dispara a requisição
                .bodyToMono(ProdutoComPreco.class);

        Mono<ProdutoComPreco> monoPreco = this.webclientPrecos
                .method(HttpMethod.GET)
                .uri("/precos/{codigo}", codigoProduto)
                .retrieve()//dispara a requisição
                .bodyToMono(ProdutoComPreco.class);


//        monoProduto.subscribe(p ->{
//            System.out.println("Agora sim finalizou de verdade");
//        });
//
//        System.out.println("Terminou Jéssica?!");
//
//        ProdutoComPreco produto = monoProduto.block();
//        ProdutoComPreco preco = monoPreco.block();
//        produto.setPreco(preco.getPreco());

        ProdutoComPreco produtoComPreco = Mono.zip(monoProduto, monoPreco).map(tuple -> {
            tuple.getT1().setPreco(tuple.getT2().getPreco());
            return tuple.getT1();
        }).block();

        return produtoComPreco;
    }
}
