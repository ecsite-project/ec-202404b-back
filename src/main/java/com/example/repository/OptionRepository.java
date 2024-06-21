package com.example.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Option;

public interface OptionRepository extends JpaRepository<Option, UUID> {
    List<Option> findByIdIn(List<UUID> uuidList);
}
