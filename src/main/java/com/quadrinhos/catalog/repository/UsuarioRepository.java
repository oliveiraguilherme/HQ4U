package com.quadrinhos.catalog.repository;

import com.quadrinhos.catalog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<User, Long> {
}
