package br.com.maferfs.mashup_produtos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Produto {
    private String codigo;
    private String nome;
    private String descricao;
}
