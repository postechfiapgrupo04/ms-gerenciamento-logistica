package br.com.fiap.mslogistica.service;

import br.com.fiap.mslogistica.model.DeliveryDriver;
import br.com.fiap.mslogistica.repository.DeliveryDriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryDriverService {

    private final DeliveryDriverRepository deliveryDriverRepository;

    public DeliveryDriverService(DeliveryDriverRepository deliveryDriverRepository) {
        this.deliveryDriverRepository = deliveryDriverRepository;
    }

    public DeliveryDriver createDeliveryDriver(DeliveryDriver deliveryDriver) {
        return deliveryDriverRepository.save(deliveryDriver);
    }

    public List<DeliveryDriver> listDeliveryDriver() {
        return deliveryDriverRepository.findAll();
    }

    public Optional<DeliveryDriver> findDeliveryDriverById(Long id) {
        return deliveryDriverRepository.findById(id);
    }
}
