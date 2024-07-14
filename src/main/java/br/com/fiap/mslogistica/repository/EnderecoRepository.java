package br.com.fiap.mslogistica.repository;


import br.com.fiap.mslogistica.model.Delivery;
import br.com.fiap.mslogistica.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
