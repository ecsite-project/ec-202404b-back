package com.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * オプションのドメインクラス.
 *
 * @author takeru.chugun
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;
//
//    @Column(nullable = false)
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID optionGroupId;
    @ManyToOne
    @JoinColumn(name = "option_group_id",nullable = false)
    private OptionGroup optionGroup;
}
