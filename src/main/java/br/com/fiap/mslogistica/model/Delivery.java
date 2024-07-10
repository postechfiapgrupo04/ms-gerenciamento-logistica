package br.com.fiap.mslogistica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long deliveryDriverId;

    @Column(unique = true)
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
