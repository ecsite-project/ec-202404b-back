package com.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 注文詳細のドメインクラス.
 *
 * @author takeru.chugun
 * @author mun
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID user_id;

    @Column(nullable = false)
    private Integer status;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "destination_name", columnDefinition = "text", nullable = false)
    private String destinationName;

    @Column(name = "destination_email", columnDefinition = "text", nullable = false)
    private String destinationEmail;

    @Column(name = "destination_zipcode", columnDefinition = "text", nullable = false)
    private String destinationZipcode;

    @Column(name = "destination_prefecture", columnDefinition = "text", nullable = false)
    private String destinationPrefecture;

    @Column(name = "destination_municipalities", columnDefinition = "text", nullable = false)
    private String destinationMunicipalities;

    @Column(name = "destination_address", columnDefinition = "text", nullable = false)
    private String destinationAddress;

    @Column(name = "destination_tel", columnDefinition = "text", nullable = false)
    private String destinationTel;

    @Column(name = "delivery_date", columnDefinition = "text", nullable = false)
    private String deliveryDate;

    @Column(name = "delivery_time", columnDefinition = "text", nullable = false)
    private UUID deliveryTime;

    @Column(name = "payment_method", columnDefinition = "text", nullable = false)
    private String paymentMethod;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
}
