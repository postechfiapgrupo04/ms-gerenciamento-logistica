package br.com.fiap.mslogistica.service;

import br.com.fiap.mslogistica.model.request.DeliveryRequest;
import br.com.fiap.mslogistica.model.dto.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderConsumerService {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumerService.class);

    private final DeliveryService deliveryService;

    public OrderConsumerService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @KafkaListener(topics = "order-logistic", groupId = "teste")
    public void flightEventConsumer(String order) {
        log.info("Order received: {}", order);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OrderDTO orderDTO = objectMapper.readValue(order, OrderDTO.class);
            DeliveryRequest deliveryRequest = new DeliveryRequest(UUID.fromString(orderDTO.getIdOrder()), UUID.fromString(orderDTO.getCustomerId()));
            deliveryService.assignDelivery(deliveryRequest);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter String para OrderDTO: ", e);
        }
    }

}
