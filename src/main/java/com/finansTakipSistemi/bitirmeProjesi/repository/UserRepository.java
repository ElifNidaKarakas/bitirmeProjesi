package com.finansTakipSistemi.bitirmeProjesi.repository;

import com.finansTakipSistemi.bitirmeProjesi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}