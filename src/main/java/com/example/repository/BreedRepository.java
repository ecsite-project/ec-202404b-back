package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Breed;

/**
 * 種別を操作するリポジトリクラス.
 *
 * @author tugukurechan
 */
@Repository
public interface BreedRepository extends JpaRepository<Breed, UUID> {
    public Breed findByName(String name);
}
