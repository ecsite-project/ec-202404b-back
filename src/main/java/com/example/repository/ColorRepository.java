package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, UUID> {
    public Color findByName(String name);

}