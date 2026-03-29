package com.example.demo.model.dto;

import java.util.List;

import com.example.demo.model.enums.TicketStatus;

public record NewTicketDTO(
    Integer id,
    Integer id_solicitante,
    Integer id_destinatario,
    String acao,
    String equipamento,
    String detalhes,
    List<String> observadores,
    TicketStatus status
) {}
