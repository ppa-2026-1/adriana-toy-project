package com.example.demo.model;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.NewTicketDTO;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Ticket;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public void registerNewTicket(NewTicketDTO newTicket) {

        Integer solicitanteId = newTicket.id_solicitante();
        if (solicitanteId == null || solicitanteId <= 0) {
            throw new IllegalArgumentException("Informe o id do solicitante");
        }

        Integer responsavelIdTemp = newTicket.id_responsavel();
        if (responsavelIdTemp == null || responsavelIdTemp <= 0) {
            responsavelIdTemp = solicitanteId;
        }
        final Integer responsavelId = responsavelIdTemp;

        Integer destinatarioId = newTicket.id_destinatario();
        if (destinatarioId == null || destinatarioId <= 0) {
            throw new IllegalArgumentException("Informe o id do destinatário");
        }

        if (newTicket.equipamento() == null || newTicket.equipamento().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o equipamento");
        }

        if (newTicket.detalhes() == null || newTicket.detalhes().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe os detalhes");
        }

        userRepository.findById(solicitanteId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuário solicitante " + solicitanteId + " não encontrado"));

        userRepository.findById(destinatarioId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuário destinatário " + destinatarioId + " não encontrado"));

        userRepository.findById(responsavelId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuário responsável " + responsavelId + " não encontrado"));


        Ticket ticket = new Ticket();
        ticket.setId_solicitante(solicitanteId);
        ticket.setId_destinatario(destinatarioId);
        ticket.setId_responsavel(responsavelId);
        ticket.setEquipamento(newTicket.equipamento());
        ticket.setDetalhes(newTicket.detalhes());
        ticket.setObservadores(newTicket.observadores());
        ticket.setStatus(newTicket.status());

        ticketRepository.save(ticket);
    }
}