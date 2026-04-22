package org.iti.soap.dao;

import jakarta.persistence.EntityManager;
import org.iti.soap.config.JPAUtil;
import org.iti.soap.entity.Country;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class CountryDao {

    public Optional<Country> findById(Short id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            Country country = em.find(Country.class, id);
            return Optional.ofNullable(country);
        } finally {
            em.close();
        }
    }

    public List<Country> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Country c ORDER BY c.country",
                    Country.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public Country save(Country country) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();

            country.setLastUpdate(Instant.now()); // optional (DB also handles it)
            em.persist(country);

            em.getTransaction().commit();
            return country;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to save country", e);
        } finally {
            em.close();
        }
    }

    public Country update(Country country) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();

            Country managed = em.merge(country);
            managed.setLastUpdate(Instant.now());
            em.getTransaction().commit();
            return managed;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to update country", e);
        } finally {
            em.close();
        }
    }

    public boolean deleteById(Short id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();

            Country country = em.find(Country.class, id);
            if (country == null) {
                em.getTransaction().commit();
                return false;
            }

            em.remove(country);

            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to delete country (may be referenced by cities)", e);
        } finally {
            em.close();
        }
    }
}