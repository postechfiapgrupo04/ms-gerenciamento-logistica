package br.com.fiap.mslogistica.model.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private UUID idOrder;
    private UUID customerId;
    private String status;
    private List<OrderItemDTO> items;

}
