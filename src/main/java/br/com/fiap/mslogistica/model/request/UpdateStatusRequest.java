package br.com.fiap.mslogistica.model.request;

import br.com.fiap.mslogistica.model.DeliveryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateStatusRequest {

    private UUID orderId;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;
}