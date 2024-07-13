package br.com.fiap.mslogistica.controller;

import br.com.fiap.mslogistica.model.Delivery;
import br.com.fiap.mslogistica.model.request.UpdateStatusRequest;
import br.com.fiap.mslogistica.repository.DeliveryDriverRepository;
import br.com.fiap.mslogistica.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryDriverRepository deliveryDriverRepository;

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/updateDeliveryStatus")
    public Optional<Delivery> updateDeliveryStatus(@RequestBody UpdateStatusRequest updateStatusRequest) {
        if (updateStatusRequest.getOrderId() == null)
            throw new RuntimeException("OrderId não pode estar vazio");
        return deliveryService.updateDeliveryStatus(updateStatusRequest);
    }

    @GetMapping("/findDeliverys")
    public List<Delivery> findDeliverys() {
        return deliveryService.findDeliverys();
    }

    @GetMapping("/findByOrderId")
    public Delivery findByOrderId(@RequestParam UUID orderId) {
        return deliveryService.findByOrderId(orderId);
    }

}
