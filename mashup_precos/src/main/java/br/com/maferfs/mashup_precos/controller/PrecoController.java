package br.com.maferfs.mashup_precos.controller;

import br.com.maferfs.mashup_precos.model.Preco;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/precos")
public class PrecoController {

    @GetMapping("/{codigoProduto}")
    public ResponseEntity<Preco> obterPreco(@PathVariable Long codigoProduto){

        Preco preco = new Preco(codigoProduto, new BigDecimal(100));
        return ResponseEntity.ok(preco);
    }
}
