package com.example.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * オプションのグループを示すドメインクラス.
 *
 * @author takeru.chugun
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "option_groups")
public class OptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String inputType;

    @OneToMany(mappedBy = "optionGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Option> options = new ArrayList<>();
}
