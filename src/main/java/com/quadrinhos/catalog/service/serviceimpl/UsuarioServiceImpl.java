package com.quadrinhos.catalog.service.serviceimpl;

import com.quadrinhos.catalog.model.User;
import com.quadrinhos.catalog.repository.UsuarioRepository;
import com.quadrinhos.catalog.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public void save(User user) {
        usuarioRepository.save(user);
    }
}
