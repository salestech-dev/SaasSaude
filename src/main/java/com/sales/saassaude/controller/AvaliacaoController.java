package com.sales.saassaude.controller;

import com.sales.saassaude.models.AvaliacaoSaude;
import com.sales.saassaude.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    // REGISTRAR NOVA AVALIAÇÃO
    @PostMapping
    public ResponseEntity<?> registrarAvaliacao(@RequestBody AvaliacaoSaude avaliacao) {
        try {
            AvaliacaoSaude avaliacaoSalva = avaliacaoService.registrarAvaliacao(avaliacao);
            return ResponseEntity.ok(avaliacaoSalva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // BUSCAR AVALIAÇÃO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoSaude> buscarPorId(@PathVariable Long id) {
        try {
            AvaliacaoSaude avaliacao = avaliacaoService.buscarPorId(id);
            return ResponseEntity.ok(avaliacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // HISTÓRICO DE AVALIAÇÕES DE UM FUNCIONÁRIO
    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<AvaliacaoSaude>> historicoFuncionario(@PathVariable Long funcionarioId) {
        List<AvaliacaoSaude> avaliacoes = avaliacaoService.buscarHistoricoFuncionario(funcionarioId);
        return ResponseEntity.ok(avaliacoes);
    }

    // TODAS AVALIAÇÕES DE UMA EMPRESA
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<AvaliacaoSaude>> avaliacoesPorEmpresa(@PathVariable Long empresaId) {
        List<AvaliacaoSaude> avaliacoes = avaliacaoService.buscarAvaliacoesPorEmpresa(empresaId);
        return ResponseEntity.ok(avaliacoes);
    }

    // AVALIAÇÕES POR TIPO (FISICA, PSICOLOGICA, etc)
    @GetMapping("/empresa/{empresaId}/tipo/{tipo}")
    public ResponseEntity<List<AvaliacaoSaude>> avaliacoesPorTipo(
            @PathVariable Long empresaId, 
            @PathVariable String tipo) {
        // Aqui você precisaria converter String para EnumTipoAvaliacao
        // List<AvaliacaoSaude> avaliacoes = avaliacaoService.buscarPorTipo(empresaId, tipo);
        return ResponseEntity.ok(List.of()); // Placeholder
    }
}