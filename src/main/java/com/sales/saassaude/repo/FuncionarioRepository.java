package com.sales.saassaude.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sales.saassaude.models.Funcionario;
import org.springframework.stereotype.Repository;


@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario>findByEmpresaId(Long empresaId);
    List<Funcionario>findByEmpresaIdAndAtivoTrue(Long empresaId);
    Optional<Funcionario>findByEmail(String email);
    List<Funcionario>findByCargo(String cargo);
    List<Funcionario>findByDepartamento(String departamento);
    
}
