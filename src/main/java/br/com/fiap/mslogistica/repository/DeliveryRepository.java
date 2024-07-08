package br.com.fiap.mslogistica.repository;


import br.com.fiap.mslogistica.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
