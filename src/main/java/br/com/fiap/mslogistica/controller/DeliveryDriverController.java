package br.com.fiap.mslogistica.controller;

import br.com.fiap.mslogistica.model.DeliveryDriver;
import br.com.fiap.mslogistica.service.DeliveryDriverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deliveryDriver")
public class DeliveryDriverController {

    private final DeliveryDriverService deliveryDriverService;

    public DeliveryDriverController(DeliveryDriverService deliveryDriverService) {
        this.deliveryDriverService = deliveryDriverService;
    }

    @PostMapping("/create")
    public DeliveryDriver createDeliveryDriver(@RequestBody DeliveryDriver deliveryDriver) {
        return deliveryDriverService.createDeliveryDriver(deliveryDriver);
    }

    @GetMapping("/findAll")
    public List<DeliveryDriver> listAllDeliveryDriver() {
        return deliveryDriverService.listDeliveryDriver();
    }

    @GetMapping("/{id}/findDeliveryDriver")
    public Optional<DeliveryDriver> findDeliveryDriverById(@PathVariable Long id) {
        return deliveryDriverService.findDeliveryDriverById(id);
    }
}
