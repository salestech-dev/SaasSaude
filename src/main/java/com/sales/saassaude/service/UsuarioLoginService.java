package com.sales.saassaude.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sales.saassaude.models.UsuarioLogin;
import com.sales.saassaude.repo.UsuarioLoginRepository;

import java.util.*;

@Service
public class UsuarioLoginService {

    @Autowired
    private UsuarioLoginRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder; // <- Codificador injetado.

    public UsuarioLogin cadastrarUsuarioLogin(UsuarioLogin user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username já cadastrado");
        }

        String senhaCriptografada = passwordEncoder.encode(user.getPassword());
        user.setPassword(senhaCriptografada);
        user.setAtivo(true);
        return userRepo.save(user);
    }

    public boolean autenticar(String userName, String senhaDigitada){
        Optional<UsuarioLogin> userOpt = userRepo.findByUsername(userName);
        if(userOpt.isEmpty()){
            return false;
        }

        UsuarioLogin usuario = userOpt.get();
        return passwordEncoder.matches(senhaDigitada, usuario.getPassword());

    }

        public Optional<UsuarioLogin> buscarPorUsername(String username) {
        return userRepo.findByUsername(username);
    }

}
