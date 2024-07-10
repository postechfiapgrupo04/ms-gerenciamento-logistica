package br.com.fiap.mslogistica.controller;

import br.com.fiap.mslogistica.model.Delivery;
import br.com.fiap.mslogistica.model.DeliveryDriver;
import br.com.fiap.mslogistica.repository.DeliveryDriverRepository;
import br.com.fiap.mslogistica.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryDriverRepository deliveryDriverRepository;

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/assignDeliveryDriver")
    public Delivery assignDeliveryDriver(@RequestBody Delivery delivery) {
        Optional<DeliveryDriver> deliveryDriver = Optional.ofNullable(deliveryDriverRepository.findById(delivery.getDeliveryDriverId())
                .orElseThrow(() -> new RuntimeException("Entregador não encontrado. ID: " + delivery.getDeliveryDriverId())));
        return deliveryService.assignDelivery(delivery);
    }

    @GetMapping("/findDeliverys")
    public List<Delivery> findDeliverys() {
        return deliveryService.findDeliverys();
    }

}
