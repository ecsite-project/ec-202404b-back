package com.example.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Option;

/**
 * オプションを操作するリポジトリクラス.
 *
 * @author tugukurechan
 */
@Repository
public interface OptionRepository extends JpaRepository<Option, UUID> {
    List<Option> findByIdIn(List<UUID> uuidList);
    Option findByName(String name);
}
