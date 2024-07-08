package br.com.fiap.mslogistica.controller;

import br.com.fiap.mslogistica.model.Delivery;
import br.com.fiap.mslogistica.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/assignDeliveryDriver")
    public Delivery assignDeliveryDriver(@RequestBody Delivery delivery) {
        return deliveryService.assignDelivery(delivery);
    }

    @GetMapping("/findDeliverys")
    public List<Delivery> findDeliverys() {
        return deliveryService.findDeliverys();
    }
}
