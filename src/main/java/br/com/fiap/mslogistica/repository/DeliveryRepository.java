package br.com.fiap.mslogistica.repository;


import br.com.fiap.mslogistica.model.Address;
import br.com.fiap.mslogistica.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Delivery findByOrderId(UUID orderId);
}
