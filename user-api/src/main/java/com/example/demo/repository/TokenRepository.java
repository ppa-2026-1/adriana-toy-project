package com.example.demo.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Token;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class TokenRepository {

    private final EntityManager em;

    public TokenRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public Optional<Token> findByValue(String value) {
        return em.createQuery("FROM Token t WHERE t.value = :value", Token.class)
                .setParameter("value", value)
                .getResultStream()
                .findFirst();
    }

    @Transactional
    public void deleteByValue(String value) {
        em.createQuery("DELETE FROM Token t WHERE t.value = :value")
                .setParameter("value", value)
                .executeUpdate();
    }

    @Transactional
    public void save(Token token) {
        if (token.getId() == null) {
            em.persist(token);
        } else {
            em.merge(token);
        }
    }
}
