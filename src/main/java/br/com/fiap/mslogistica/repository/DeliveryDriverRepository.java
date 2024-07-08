package br.com.fiap.mslogistica.repository;


import br.com.fiap.mslogistica.model.DeliveryDriver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Long> {
}
