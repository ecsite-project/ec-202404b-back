package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.OptionGroup;

/**
 * オプションのグループを操作するリポジトリクラス.
 *
 * @author tugukurechan
 */
@Repository
public interface OptionGroupRepository extends JpaRepository<OptionGroup, UUID> {
    public OptionGroup findByName(String name);
}
