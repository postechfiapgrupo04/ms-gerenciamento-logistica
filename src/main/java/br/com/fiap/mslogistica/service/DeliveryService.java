package br.com.fiap.mslogistica.service;

import br.com.fiap.mslogistica.model.Delivery;
import br.com.fiap.mslogistica.model.dto.DeliveryDTO;
import br.com.fiap.mslogistica.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Delivery assignDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> findDeliverys() {
        return deliveryRepository.findAll();
    }
}
