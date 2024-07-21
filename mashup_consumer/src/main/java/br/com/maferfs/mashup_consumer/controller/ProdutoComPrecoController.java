package br.com.maferfs.mashup_consumer.controller;

import br.com.maferfs.mashup_consumer.model.ProdutoComPreco;
import br.com.maferfs.mashup_consumer.service.PrecoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ProdutoComPrecoController {

    @Autowired
    private PrecoProdutoService precoProdutoService;

    @GetMapping("/produto/{codigo}/preco")
    public ResponseEntity<ProdutoComPreco> obterProdutoComPreco(@PathVariable Long codigo){
        ProdutoComPreco produtoComPreco = this.precoProdutoService.obterPorCodigo(codigo);

        return ResponseEntity.ok(produtoComPreco);
    }
}
