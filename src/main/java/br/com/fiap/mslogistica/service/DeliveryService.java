package br.com.fiap.mslogistica.service;

import br.com.fiap.mslogistica.model.*;
import br.com.fiap.mslogistica.model.request.DeliveryRequest;
import br.com.fiap.mslogistica.model.request.UpdateStatusRequest;
import br.com.fiap.mslogistica.repository.DeliveryDriverRepository;
import br.com.fiap.mslogistica.repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryService {

    private final String stockUrl = "http://localhost:8001/user";

    private static final Logger log = LoggerFactory.getLogger(DeliveryService.class);
    private final DeliveryRepository deliveryRepository;
    private final EnderecoService enderecoService;
    private final DeliveryDriverService deliveryDriverService;

    //TODO trocar todos os repository por service
    public DeliveryService(DeliveryRepository deliveryRepository, EnderecoService enderecoService, DeliveryDriverService deliveryDriverService) {
        this.deliveryRepository = deliveryRepository;
        this.enderecoService = enderecoService;
        this.deliveryDriverService = deliveryDriverService;
    }

    public void assignDelivery(DeliveryRequest deliveryRequest) {
        Optional<DeliveryDriver> availableDriverOptional = Optional.ofNullable(deliveryDriverService.getDeliveryDriverAvailable()
                .orElseThrow(() -> new RuntimeException("Nenhum motorista está disponível")));

        ResponseEntity<Client> client = getClientInfo(deliveryRequest.getCustomerId());
        if (client.getBody() == null) {
            throw new RuntimeException("Erro ao obter informações do cliente");
        }
        Optional<Endereco> enderecoOptional = Optional.ofNullable(client.getBody().getEndereco());

        enderecoOptional.ifPresent(endereco -> availableDriverOptional.ifPresent(deliveryDriver -> {
            enderecoService.saveAddress(endereco);
            deliveryDriverService.addDelivery(deliveryDriver);
            Delivery delivery = new Delivery(deliveryDriver.getId(), deliveryRequest.getOrderId(), endereco, DeliveryStatus.CREATED);
            deliveryRepository.save(delivery);
        }));
    }

    private ResponseEntity<Client> getClientInfo(UUID customerId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = stockUrl + "/id?id=" + customerId;
        return restTemplate.getForEntity(url, Client.class);
    }

    public Optional<Delivery> updateDeliveryStatus(UpdateStatusRequest updateStatusRequest) {
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

    public Delivery findByOrderId(UUID orderId) {
        return deliveryRepository.findByOrderId(orderId);
    }
}
