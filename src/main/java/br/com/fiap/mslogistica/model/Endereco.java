package br.com.fiap.mslogistica.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String rua;
    private int numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String cep;
}
