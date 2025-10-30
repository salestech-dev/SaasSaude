package com.sales.saassaude.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cargo;

    private LocalDateTime dataAdmissao;

    @JoinColumn(name = "empresa_id")
    @ManyToOne
    private Empresa empresa;

    private String departamento;

    @Column(nullable = false)
    private boolean ativo;



// GETTERS E SETTER

    public String getCargo() {
        return cargo;
    }

    public LocalDateTime getDataAdmissao() {
        return dataAdmissao;
    }

    public String getEmail() {
        return email;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Long getId() {
        return Id;
    }

    public String getNome() {
        return nome;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setDataAdmissao(LocalDateTime dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
