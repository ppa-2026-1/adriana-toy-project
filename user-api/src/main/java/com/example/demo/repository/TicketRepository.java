package com.example.demo.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.example.demo.repository.entity.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class TicketRepository {

    private final EntityManager em;

    public TicketRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<Ticket> findById(Integer id) {
        return em.createQuery("FROM Ticket t WHERE t.id = :id", Ticket.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    public Optional<Ticket> findByEquipment(String equipamento) {
        return em.createQuery("FROM Ticket t WHERE t.equipamento = :handle", Ticket.class)
                .setParameter("equipamento", equipamento)
                .getResultStream()
                .findFirst();
    }

    public List<Ticket> findAll() {
        return em.createQuery("FROM Ticket t", Ticket.class)
                .getResultList();
    }

    @Transactional
    public void save(Ticket Ticket) {
        if (Ticket.getId() == null) {
            em.persist(Ticket);
        } else {
            em.merge(Ticket);
        }
    }
}
