package com.sales.saassaude.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sales.saassaude.models.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
    Optional<Empresa> findByCnpj(String cnpj);
    boolean existsByCnpj(String cnpj);
    List<Empresa> findByNomeContainingIgnoreCase(String nome);
    List<Empresa> findByAtivoTrue();
    Optional<Empresa> findByEmail(String email);
}