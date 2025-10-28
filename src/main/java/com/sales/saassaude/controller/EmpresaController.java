package com.sales.saassaude.controller;

import com.sales.saassaude.models.Empresa;
import com.sales.saassaude.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    // LISTAR TODAS EMPRESAS ATIVAS
    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        List<Empresa> empresas = empresaService.listarAtivas();
        return ResponseEntity.ok(empresas);
    }

    // BUSCAR EMPRESA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable Long id) {
        try {
            Empresa empresa = empresaService.buscarPorId(id);
            return ResponseEntity.ok(empresa);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // CADASTRAR NOVA EMPRESA
    @PostMapping
    public ResponseEntity<?> cadastrarEmpresa(@RequestBody Empresa empresa) {
        try {
            Empresa empresaSalva = empresaService.cadastrar(empresa);
            return ResponseEntity.ok(empresaSalva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ATUALIZAR EMPRESA
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        try {
            Empresa empresaAtualizada = empresaService.atualizar(id, empresa);
            return ResponseEntity.ok(empresaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // INATIVAR EMPRESA
    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativarEmpresa(@PathVariable Long id) {
        try {
            empresaService.inativar(id);
            return ResponseEntity.ok("Empresa inativada com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}