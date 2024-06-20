package com.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * OrderItemのドメインクラス.
 *
 * @author takeru.chugun
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToMany
    @JoinTable(
            name = "order_item_options",
            joinColumns = @JoinColumn(name = "order_item_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> options;
}
