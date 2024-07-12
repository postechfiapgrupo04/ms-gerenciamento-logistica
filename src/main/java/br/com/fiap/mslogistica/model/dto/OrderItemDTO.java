package br.com.fiap.mslogistica.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private String productId;
    private Long quantity;
    private Double price;

}
