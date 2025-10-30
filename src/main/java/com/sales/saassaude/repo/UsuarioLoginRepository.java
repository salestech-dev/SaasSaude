package com.sales.saassaude.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.sales.saassaude.models.EnumRole;
import com.sales.saassaude.models.UsuarioLogin;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioLoginRepository extends JpaRepository<UsuarioLogin, Long> {
Optional<UsuarioLogin>findByUsername(String username);
Optional<UsuarioLogin>findByEmpresaId(Long empresaId);
List<UsuarioLogin>findByRole(EnumRole role);
boolean existsByUsername(String username);
}
