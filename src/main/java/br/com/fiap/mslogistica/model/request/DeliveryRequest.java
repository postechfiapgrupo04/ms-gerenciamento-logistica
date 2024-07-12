package br.com.fiap.mslogistica.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DeliveryRequest {
    private UUID orderId;
    private UUID customerId;
}
