package com.example.demo.model.dto;

import com.example.demo.model.enums.TicketStatus;

public record UpdateTicketDTO(
    Integer id_responsavel,
    TicketStatus status,
    String motivo
) {}
