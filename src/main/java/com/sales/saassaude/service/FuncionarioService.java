package com.sales.saassaude.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.saassaude.models.Funcionario;
import com.sales.saassaude.models.UsuarioLogin;
import com.sales.saassaude.repo.EmpresaRepository;
import com.sales.saassaude.repo.FuncionarioRepository;

import java.util.*;

@Service
public class FuncionarioService {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario cadastrarFuncionario(Funcionario funcionario) {
        if (!empresaRepository.existsById(funcionario.getEmpresa().getId())) {
            throw new RuntimeException("EMPRESA NÃO ECONTRADA");
        }

        if (funcionarioRepository.findByEmail(funcionario.getEmail()).isPresent()) {
            throw new RuntimeException("EMAIL JÁ CADASTRADO");
        }

        return funcionarioRepository.save(funcionario);
    }

    public Funcionario procurarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public List<Funcionario> listarPorEmpresa(Long empresaId) {
        return funcionarioRepository.findByEmpresaId(empresaId);
    }

    public List<Funcionario> listarAtivosPorEmpresa(Long empresaId) {
        return funcionarioRepository.findByEmpresaIdAndAtivoTrue(empresaId);
    }

    public void atualizarFuncionario(Long id, Funcionario dadosAtualizados) {
        Funcionario funcionario = procurarPorId(id);

        // VALIDA EMAIL (se mudou)
        if (!funcionario.getEmail().equals(dadosAtualizados.getEmail())) {
            Optional<Funcionario> existente = funcionarioRepository.findByEmail(dadosAtualizados.getEmail());
            if (existente.isPresent() && !existente.get().getId().equals(id)) {
                throw new RuntimeException("EMAIL JÁ CADASTRADO");
            }
        }

        funcionario.setNome(dadosAtualizados.getNome());
        funcionario.setEmail(dadosAtualizados.getEmail());
        funcionario.setCargo(dadosAtualizados.getCargo());
        funcionario.setDataAdmissao(dadosAtualizados.getDataAdmissao());

        funcionarioRepository.save(funcionario);
    }

    public boolean emailExistente(String email) {
        return funcionarioRepository.findByEmail(email).isPresent();
    }

    public void inativarFuncionario(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inativarFuncionario'");
    }
}
