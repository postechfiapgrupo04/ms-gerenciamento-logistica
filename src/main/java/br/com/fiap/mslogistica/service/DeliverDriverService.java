package br.com.fiap.mslogistica.service;

import br.com.fiap.mslogistica.model.DeliveryDriver;
import br.com.fiap.mslogistica.repository.DeliveryDriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliverDriverService {

    private final DeliveryDriverRepository deliveryDriverRepository;

    public DeliverDriverService(DeliveryDriverRepository deliveryDriverRepository) {
        this.deliveryDriverRepository = deliveryDriverRepository;
    }

    public DeliveryDriver createDeliveryDriver(DeliveryDriver deliveryDriver) {
        return deliveryDriverRepository.save(deliveryDriver);
    }

    public List<DeliveryDriver> listDeliveryDriver() {
        return deliveryDriverRepository.findAll();
    }
}
