package com.sales.saassaude.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sales.saassaude.models.AvaliacaoSaude;
import com.sales.saassaude.models.EnumTipoAvaliacao;

public interface AvaliacaoSaudeRepository extends JpaRepository<AvaliacaoSaude, Long> {
    List<AvaliacaoSaude> findByFuncionarioId(Long funcionarioId);
    List<AvaliacaoSaude> findByTipoAvaliacao(EnumTipoAvaliacao tipoAvaliacao);
    Optional<AvaliacaoSaude> findByFuncionarioIdAndFuncionarioEmpresaId(Long funcionarioId, Long empresaId);
    List<AvaliacaoSaude> findByFuncionarioEmpresaId(Long empresaId);
   
}
