package com.example.demo.model;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.NewTicketDTO;
import com.example.demo.model.dto.UpdateTicketDTO;
import com.example.demo.model.enums.TicketStatus;
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

        Integer destinatarioTemp = newTicket.id_destinatario();
        if (destinatarioTemp == null || destinatarioTemp <= 0) {
            destinatarioTemp = solicitanteId;
        }
        final Integer destinatarioId = destinatarioTemp;

        if (newTicket.acao() == null || newTicket.acao().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe a ação a ser ralizada");
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


        Ticket ticket = new Ticket();
        ticket.setId_solicitante(solicitanteId);
        ticket.setId_destinatario(destinatarioId);
        ticket.setEquipamento(newTicket.equipamento());
        ticket.setAcao(newTicket.acao());
        ticket.setDetalhes(newTicket.detalhes());
        ticket.setObservadores(newTicket.observadores());
        ticket.setStatus(TicketStatus.PENDENTE);

        LocalDateTime now = LocalDateTime.now();
        ticket.setCreatedAt(now);
        ticket.setUpdatedAt(now);

        ticketRepository.save(ticket);
    }

    public void updateTicket(Integer id, UpdateTicketDTO updateTicket) {
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Informe o id do ticket");
        }

        if (updateTicket.id_responsavel() == null || updateTicket.id_responsavel() <= 0) {
            throw new IllegalArgumentException("Informe o id do responsável");
        }

        if (updateTicket.status() == null || updateTicket.status() != TicketStatus.CANCELADO && updateTicket.status() != TicketStatus.CONCLUIDO) {
            throw new IllegalArgumentException("Status inválido, o status deve ser CONCLUIDO ou CANCELADO");
        }

        if (updateTicket.motivo() == null && updateTicket.status() == (TicketStatus.CANCELADO)) {
            throw new IllegalArgumentException("Informe o motivo do cancelamento");
        }

        userRepository.findById(updateTicket.id_responsavel())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuário solicitante " + updateTicket.id_responsavel() + " não encontrado"));

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Ticket " + id + " não encontrado"));
                        

        ticket.setId_responsavel(updateTicket.id_responsavel());
        ticket.setStatus(updateTicket.status());

        if (updateTicket.status() == TicketStatus.CANCELADO) {
            ticket.setMotivo(updateTicket.motivo());
        }

        LocalDateTime now = LocalDateTime.now();
        ticket.setUpdatedAt(now);

        ticketRepository.save(ticket);
    }

    public void deleteTicket(Integer id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Ticket " + id + " não encontrado"));
        ticketRepository.delete(ticket);
    }
    
}