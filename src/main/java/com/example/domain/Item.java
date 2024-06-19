package com.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 商品のドメインクラス.
 *
 * @author takeru.chugun
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate birthDay;

    @Column(nullable = false)
    private String deleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "breed_id", nullable = false)
    private Breed breed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;
}
