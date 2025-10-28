package com.sales.saassaude.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacoes_saude")
public class AvaliacaoSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @Enumerated(EnumType.STRING)
    private EnumTipoAvaliacao tipoAvaliacao;

    private LocalDateTime dataAvaliacao;

    // CAMPOS PARA AVALIAÇÃO FÍSICA
    private Double peso;
    private Double altura;
    private Double imc;
    private String pressaoArterial;
    private Integer frequenciaCardiaca;

    // CAMPOS PARA AVALIAÇÃO PSICOLÓGICA
    private Integer nivelEstresse; // 1-10
    private Integer qualidadeSono; // 1-10
    private String observacoes;

    // CAMPOS PARA AVALIAÇÃO NUTRICIONAL
    private String habitosAlimentares;
    private Integer consumoAgua; // copos por dia
    private Boolean praticaAtividadeFisica;

    private String profissionalResponsavel;

    // CONSTRUTORES
    public AvaliacaoSaude() {}

    public AvaliacaoSaude(Long id, Funcionario funcionario, EnumTipoAvaliacao tipoAvaliacao, LocalDateTime dataAvaliacao, Double peso, Double altura, Double imc, String pressaoArterial, Integer frequenciaCardiaca, Integer nivelEstresse, Integer qualidadeSono, String observacoes, String habitosAlimentares, Integer consumoAgua, Boolean praticaAtividadeFisica, String profissionalResponsavel) {
        this.id = id;
        this.funcionario = funcionario;
        this.tipoAvaliacao = tipoAvaliacao;
        this.dataAvaliacao = dataAvaliacao;
        this.peso = peso;
        this.altura = altura;
        this.imc = imc;
        this.pressaoArterial = pressaoArterial;
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.nivelEstresse = nivelEstresse;
        this.qualidadeSono = qualidadeSono;
        this.observacoes = observacoes;
        this.habitosAlimentares = habitosAlimentares;
        this.consumoAgua = consumoAgua;
        this.praticaAtividadeFisica = praticaAtividadeFisica;
        this.profissionalResponsavel = profissionalResponsavel;
    }

    // GETTERS E SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public EnumTipoAvaliacao getTipoAvaliacao() { return tipoAvaliacao; }
    public void setTipoAvaliacao(EnumTipoAvaliacao tipoAvaliacao) { this.tipoAvaliacao = tipoAvaliacao; }

    public LocalDateTime getDataAvaliacao() { return dataAvaliacao; }
    public void setDataAvaliacao(LocalDateTime dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }

    public Double getImc() { return imc; }
    public void setImc(Double imc) { this.imc = imc; }

    public String getPressaoArterial() { return pressaoArterial; }
    public void setPressaoArterial(String pressaoArterial) { this.pressaoArterial = pressaoArterial; }

    public Integer getFrequenciaCardiaca() { return frequenciaCardiaca; }
    public void setFrequenciaCardiaca(Integer frequenciaCardiaca) { this.frequenciaCardiaca = frequenciaCardiaca; }

    public Integer getNivelEstresse() { return nivelEstresse; }
    public void setNivelEstresse(Integer nivelEstresse) { this.nivelEstresse = nivelEstresse; }

    public Integer getQualidadeSono() { return qualidadeSono; }
    public void setQualidadeSono(Integer qualidadeSono) { this.qualidadeSono = qualidadeSono; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public String getHabitosAlimentares() { return habitosAlimentares; }
    public void setHabitosAlimentares(String habitosAlimentares) { this.habitosAlimentares = habitosAlimentares; }

    public Integer getConsumoAgua() { return consumoAgua; }
    public void setConsumoAgua(Integer consumoAgua) { this.consumoAgua = consumoAgua; }

    public Boolean getPraticaAtividadeFisica() { return praticaAtividadeFisica; }
    public void setPraticaAtividadeFisica(Boolean praticaAtividadeFisica) { this.praticaAtividadeFisica = praticaAtividadeFisica; }

    public String getProfissionalResponsavel() { return profissionalResponsavel; }
    public void setProfissionalResponsavel(String profissionalResponsavel) { this.profissionalResponsavel = profissionalResponsavel; }
}