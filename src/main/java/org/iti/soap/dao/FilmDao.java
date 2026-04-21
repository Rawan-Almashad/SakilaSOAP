package org.iti.soap.dao;

import jakarta.persistence.EntityManager;
import org.iti.soap.config.JPAUtil;
import org.iti.soap.entity.Film;

import java.util.List;
import java.util.Optional;
import java.util.jar.JarOutputStream;

public class FilmDao {
    public Film save(Film film)
    {
        EntityManager em= JPAUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(film);
            em.getTransaction().commit();
            return film;
        }
        catch (Exception e)
        {
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw new RuntimeException("Couldn't save film");
        }
        finally {
            em.close();
        }
    }
    public Optional<Film> findById(Short id) {
        EntityManager em= JPAUtil.getEntityManagerFactory().createEntityManager();
        return Optional.ofNullable(em.find(Film.class,id));
    }
    public List<Film> findAll(){
        EntityManager em= JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("select f from Film f",Film.class).getResultList();
    }
    public void delete(Short id) {
        EntityManager em= JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            Film film=em.find(Film.class,id);
            if(film==null)
                throw new RuntimeException("Film not found");
            em.getTransaction().begin();
            em.remove(film);
            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw new RuntimeException("Couldn't delete the film");
        }
        finally {
            em.close();
        }
    }
    public Film findWithLanguage(Short id)
    {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return (Film) em.createQuery("select f from Film f join fetch f.language where f.filmId =:id").setParameter("id",id)
                .getSingleResult();
    }
}
