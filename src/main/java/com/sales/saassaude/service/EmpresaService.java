package com.sales.saassaude.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.saassaude.models.Empresa;
import com.sales.saassaude.repo.EmpresaRepository;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repo;

    public Empresa cadastrar(Empresa empresa) {
        if (repo.existsByCnpj(empresa.getCnpj())) {
            throw new RuntimeException("CNPJ JA CADASTRADO");
        }

        if (repo.findByEmail(empresa.getEmail()).isPresent()) {
            throw new RuntimeException("EMAIL JA CADASTRADO");
        }

        empresa.setAtivo(true);
        empresa.setDataCadastro(LocalDateTime.now());

        return repo.save(empresa);

    }

    public Empresa buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("EMPRESA NÃO ECONTRADA"));
    }

    public List<Empresa> listarAtivas() {
        return repo.findByAtivoTrue();
    }

    public Empresa atualizar(Long id, Empresa dadosAtualizados) {
        Empresa empresa = buscarPorId(id);

        empresa.setNome(dadosAtualizados.getNome());
        empresa.setTelefone(dadosAtualizados.getTelefone());
        empresa.setEndereco(dadosAtualizados.getEndereco());
        return repo.save(empresa);
    }

    public void inativar(Long id) {
        Empresa empresa = buscarPorId(id);
        empresa.setAtivo(false);
        repo.save(empresa);
    }

    public boolean cnpjExistente(String cnpj) {
        return repo.existsByCnpj(cnpj);
    }

}
