package br.com.fiap.mslogistica.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String idOrder;
    private String customerId;
    private String status;
    private List<OrderItemDTO> items;

}
