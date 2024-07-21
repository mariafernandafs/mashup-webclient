package br.com.maferfs.mashup_produtos.controller;

import br.com.maferfs.mashup_produtos.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> obterProduto(@PathVariable String codigo) {
        Produto produto = new Produto(codigo,"ventilador", "ventila demais");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<Produto> cadastraProduto(@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }
}
