package br.com.fiap.mslogistica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
//@Entity
public class Client {

    private String id;
    private String cpf;
    private String nome;
    private String email;
    private String documento;
    private Endereco endereco;
}
