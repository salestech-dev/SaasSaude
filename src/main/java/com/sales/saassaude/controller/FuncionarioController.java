package com.sales.saassaude.controller;

import com.sales.saassaude.models.Funcionario;
import com.sales.saassaude.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // LISTAR FUNCIONÁRIOS POR EMPRESA
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Funcionario>> listarPorEmpresa(@PathVariable Long empresaId) {
        List<Funcionario> funcionarios = funcionarioService.listarPorEmpresa(empresaId);
        return ResponseEntity.ok(funcionarios);
    }

    // LISTAR FUNCIONÁRIOS ATIVOS POR EMPRESA
    @GetMapping("/empresa/{empresaId}/ativos")
    public ResponseEntity<List<Funcionario>> listarAtivosPorEmpresa(@PathVariable Long empresaId) {
        List<Funcionario> funcionarios = funcionarioService.listarAtivosPorEmpresa(empresaId);
        return ResponseEntity.ok(funcionarios);
    }

    // BUSCAR FUNCIONÁRIO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        try {
            Funcionario funcionario = funcionarioService.procurarPorId(id);
            return ResponseEntity.ok(funcionario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // CADASTRAR NOVO FUNCIONÁRIO
    @PostMapping
    public ResponseEntity<?> cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        try {
            Funcionario funcionarioSalvo = funcionarioService.cadastrarFuncionario(funcionario);
            return ResponseEntity.ok(funcionarioSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ATUALIZAR FUNCIONÁRIO
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        try {
            funcionarioService.atualizarFuncionario(id, funcionario);
            return ResponseEntity.ok("Funcionário atualizado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // INATIVAR FUNCIONÁRIO
    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativarFuncionario(@PathVariable Long id) {
        try {
            funcionarioService.inativarFuncionario(id);
            return ResponseEntity.ok("Funcionário inativado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}