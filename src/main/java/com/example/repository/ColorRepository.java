package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Color;

/**
 * 色を操作するリポジトリクラス.
 *
 * @author tugukurechan
 */
@Repository
public interface ColorRepository extends JpaRepository<Color, UUID> {
    public Color findByName(String name);

}
