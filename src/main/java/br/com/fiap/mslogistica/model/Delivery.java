package br.com.fiap.mslogistica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Long deliveryDriverId;

    @Column(unique = true, nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Delivery(Long deliveryDriverId, UUID orderId, Endereco endereco, DeliveryStatus status) {
        this.deliveryDriverId = deliveryDriverId;
        this.orderId = orderId;
        this.status = status;
        this.endereco = endereco;
    }
}
