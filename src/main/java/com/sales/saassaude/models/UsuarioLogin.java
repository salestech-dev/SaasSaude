package com.sales.saassaude.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_login")
public class UsuarioLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private EnumRole role;

    @Column(nullable = false)
    @ManyToOne
    private Empresa empresa;

    @Column()
    private boolean ativo;

    public Empresa getEmpresa() {
        return empresa;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public EnumRole getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(EnumRole role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
