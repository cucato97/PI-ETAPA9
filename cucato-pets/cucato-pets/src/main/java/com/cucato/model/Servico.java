package com.cucato.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "servico") // Já está correto
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "pet", length = 100) // Especifica o nome exato da coluna
    private String pet;

    @NotBlank
    @Column(name = "tipo", length = 100)
    private String tipo;

    @NotBlank
    @Column(name = "horario", length = 10)
    private String horario;

    @NotBlank
    @Column(name = "tutor", length = 100)
    private String tutor;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPet() { return pet; }
    public void setPet(String pet) { this.pet = pet; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public String getTutor() { return tutor; }
    public void setTutor(String tutor) { this.tutor = tutor; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}

