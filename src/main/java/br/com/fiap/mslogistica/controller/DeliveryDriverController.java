package br.com.fiap.mslogistica.controller;

import br.com.fiap.mslogistica.model.DeliveryDriver;
import br.com.fiap.mslogistica.service.DeliverDriverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryDriver")
public class DeliveryDriverController {

    private final DeliverDriverService deliverDriverService;

    public DeliveryDriverController(DeliverDriverService deliverDriverService) {
        this.deliverDriverService = deliverDriverService;
    }

    @PostMapping("/create")
    public DeliveryDriver createDeliveryDriver(@RequestBody DeliveryDriver deliveryDriver) {
        return deliverDriverService.createDeliveryDriver(deliveryDriver);
    }

    @GetMapping("/findAll")
    public List<DeliveryDriver> listAllDeliveryDriver() {
        return deliverDriverService.listDeliveryDriver();
    }
}
