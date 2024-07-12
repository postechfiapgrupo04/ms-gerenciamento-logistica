package br.com.fiap.mslogistica.service;

import br.com.fiap.mslogistica.model.Address;
import br.com.fiap.mslogistica.model.Delivery;
import br.com.fiap.mslogistica.model.DeliveryDriver;
import br.com.fiap.mslogistica.model.request.DeliveryRequest;
import br.com.fiap.mslogistica.model.DeliveryStatus;
import br.com.fiap.mslogistica.model.request.UpdateStatusRequest;
import br.com.fiap.mslogistica.repository.DeliveryDriverRepository;
import br.com.fiap.mslogistica.repository.DeliveryRepository;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryService {

    private final String stockUrl = "http://localhost:8001/user";

    private static final Logger log = LoggerFactory.getLogger(DeliveryService.class);
    private final DeliveryRepository deliveryRepository;
    private final DeliveryDriverRepository deliveryDriverRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, DeliveryDriverRepository deliveryDriverRepository) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryDriverRepository = deliveryDriverRepository;
    }

    public void assignDelivery(DeliveryRequest deliveryRequest) {

        Optional<DeliveryDriver> availableDriver = Optional.ofNullable(getDeliveryDriverAvailable()
                .orElseThrow(() -> new RuntimeException("Nenhum motorista está disponível")));

        //Optional<ResponseEntity<Address>> address = Optional.of(getClientAddress(deliveryRequest.getCustomerId()));

        Delivery delivery = new Delivery(availableDriver.get().getId(), deliveryRequest.getOrderId(), /*address.get().getBody().getId(),*/ DeliveryStatus.CREATED);
        log.info("Delivery created: {}", delivery.getOrderId());
        deliveryRepository.save(delivery);
    }

    /*
    private ResponseEntity<Address> getClientAddress(UUID customerId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = stockUrl + "/id?id=" + customerId;
        //return restTemplate.getForEntity(url, Address.class);
        return restTemplate.exchange(url, HttpMethod.GET, entity, Address.class);
    }
     */

    private Optional<DeliveryDriver> getDeliveryDriverAvailable() {
        return deliveryDriverRepository.findAll()
                .stream()
                .filter(driver -> driver.getNumberOfDeliverys() < 15)
                .findFirst();
    }

    public Optional<Delivery> updateDeliveryStatus(UpdateStatusRequest updateStatusRequest) {
        //TODO findByOrderId
        Optional<Delivery> optionalDelivery = Optional.ofNullable(deliveryRepository.findByOrderId(updateStatusRequest.getOrderId()));
        optionalDelivery.ifPresent(delivery -> {
            delivery.setStatus(updateStatusRequest.getStatus());
            deliveryRepository.save(delivery);
        });
        return optionalDelivery;
    }

    public List<Delivery> findDeliverys() {
        return deliveryRepository.findAll();
    }
}
