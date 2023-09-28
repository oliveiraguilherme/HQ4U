package com.quadrinhos.catalog.service;

import com.quadrinhos.catalog.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {
    void save(User user);
}
