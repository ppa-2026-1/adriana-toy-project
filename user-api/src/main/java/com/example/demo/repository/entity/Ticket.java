package com.example.demo.repository.entity;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.model.enums.TicketStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String acao;

    @Column(nullable = false, length = 255)
    private String equipamento;
    
    @Column(nullable = false, length = 255)
    private String detalhes;

    @Column(nullable = false)
    private Integer id_solicitante;

    @Column(nullable = true)
    private Integer id_responsavel;

    @Column(nullable = false)
    private Integer id_destinatario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 255)
    private TicketStatus status;

    @Column(nullable = true, length = 255)
    private String motivo;

    @Column(nullable = false, length = 255)
    private List<String> observadores;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Integer getId_solicitante() {
        return id_solicitante;
    }

    public void setId_solicitante(Integer id_solicitante) {
        this.id_solicitante = id_solicitante;
    }

    public Integer getId_responsavel() {
        return id_responsavel;
    }

    public void setId_responsavel(Integer id_responsavel) {
        this.id_responsavel = id_responsavel;
    }

    public Integer getId_destinatario() {
        return id_destinatario;
    }

    public void setId_destinatario(Integer id_destinatario) {
        this.id_destinatario = id_destinatario;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public List<String> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<String> observadores) {
        this.observadores = observadores;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    

}
