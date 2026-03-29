package com.example.demo.model.dto;

import java.util.List;

import com.example.demo.model.enums.TicketStatus;

public record NewTicketDTO(
    int id,
    Integer id_solicitante,
    Integer id_responsavel,
    Integer id_destinatario,
    String equipamento,
    String detalhes,
    List<String> observadores,
    TicketStatus status
) {}
