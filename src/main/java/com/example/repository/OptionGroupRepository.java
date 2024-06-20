package com.example.repository;

import com.example.domain.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OptionGroupRepository extends JpaRepository<OptionGroup, UUID> {

}
