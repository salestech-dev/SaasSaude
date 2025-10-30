package com.sales.saassaude.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.saassaude.models.AvaliacaoSaude;
import com.sales.saassaude.models.EnumTipoAvaliacao;
import com.sales.saassaude.repo.AvaliacaoSaudeRepository;
import com.sales.saassaude.repo.FuncionarioRepository;

@Service
public class AvaliacaoService {

    private final AvaliacaoSaudeRepository avaliacaoRepository;

    private final FuncionarioRepository funcionarioRepository;

    public AvaliacaoService(AvaliacaoSaudeRepository avaliacaoRepository, FuncionarioRepository funcionarioRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public AvaliacaoSaude registrarAvaliacao(AvaliacaoSaude avaliacao) {
        // 1. VALIDAR SE FUNCIONÁRIO EXISTE
        if (!funcionarioRepository.existsById(avaliacao.getFuncionario().getId())) {
            throw new RuntimeException("Funcionário não encontrado");
        }

        // 2. VALIDAR DADOS OBRIGATÓRIOS
        validarDadosAvaliacao(avaliacao);

        // 3. CALCULAR IMC AUTOMATICAMENTE (se for avaliação FÍSICA)
        if (avaliacao.getTipoAvaliacao() == EnumTipoAvaliacao.FISICA) {
            if (avaliacao.getPeso() != null && avaliacao.getAltura() != null) { // ✅ CORRIGIDO
                Double imc = calcularIMC(avaliacao.getPeso(), avaliacao.getAltura());
                avaliacao.setImc(imc);
            }
        }

        // 4. CONFIGURAR DATA ATUAL
        avaliacao.setDataAvaliacao(LocalDateTime.now());

        // 5. SALVAR NO BANCO
        return avaliacaoRepository.save(avaliacao);
    }

    private void validarDadosAvaliacao(AvaliacaoSaude avaliacao) {
        if (avaliacao.getTipoAvaliacao() == null) {
            throw new RuntimeException("Tipo de avaliação é obrigatório");
        }

        // Validações específicas por tipo de avaliação
        switch (avaliacao.getTipoAvaliacao()) {
            case FISICA:
                if (avaliacao.getPeso() == null || avaliacao.getPeso() <= 0) { // ✅ CORRIGIDO
                    throw new RuntimeException("Peso é obrigatório para avaliação física");
                }
                if (avaliacao.getAltura() == null || avaliacao.getAltura() <= 0) { // ✅ CORRIGIDO
                    throw new RuntimeException("Altura é obrigatória para avaliação física");
                }
                break;

            case PSICOLOGICA:
                if (avaliacao.getNivelEstresse() == null) {
                    throw new RuntimeException("Nível de estresse é obrigatório para avaliação psicológica");
                }
                break;

            case NUTRICIONAL:
                if (avaliacao.getHabitosAlimentares() == null || avaliacao.getHabitosAlimentares().isEmpty()) { // ✅ CORRIGIDO
                    throw new RuntimeException("Hábitos alimentares são obrigatórios para avaliação nutricional");
                }
                break;
        }
    }

    public Double calcularIMC(Double peso, Double altura) {
        if (peso <= 0 || altura <= 0) {
            throw new RuntimeException("Peso e altura devem ser maiores que zero");
        }
        // Fórmula: IMC = peso / (altura * altura)
        return peso / (altura * altura);
    }

    public AvaliacaoSaude buscarPorId(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
    }

    // HISTÓRICO DE UM FUNCIONÁRIO
    public List<AvaliacaoSaude> buscarHistoricoFuncionario(Long funcionarioId) {
        return avaliacaoRepository.findByFuncionarioId(funcionarioId);
    }

    // TODAS AVALIAÇÕES DE UMA EMPRESA
    public List<AvaliacaoSaude> buscarAvaliacoesPorEmpresa(Long empresaId) {
        return avaliacaoRepository.findByFuncionarioEmpresaId(empresaId);
    }

    // EXCLUIR AVALIAÇÃO
    public void excluirAvaliacao(Long id) {
        if (!avaliacaoRepository.existsById(id)) {
            throw new RuntimeException("Avaliação não encontrada");
        }
        avaliacaoRepository.deleteById(id);
    }
}