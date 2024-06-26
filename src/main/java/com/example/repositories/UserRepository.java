package com.example.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * ユーザを操作するリポジトリクラス.
 *
 * @monmon018
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
