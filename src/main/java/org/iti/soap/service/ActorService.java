package org.iti.soap.service;

import jakarta.persistence.EntityManager;
import org.iti.soap.config.JPAUtil;
import org.iti.soap.dao.ActorDao;
import org.iti.soap.dto.CreateActorRequest;
import org.iti.soap.entity.Actor;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class ActorService {
    private ActorDao actorDao;
    public ActorService(ActorDao actorDao)
    {
        this.actorDao=actorDao;
    }
    public Actor create(CreateActorRequest request)
    {
        Actor actor=new Actor();
        actor.setFirstName(request.getFirstName());
        actor.setLastName(request.getLastName());
        actor.setLastUpdate(Instant.now());
        return actorDao.save(actor);
    }
    public Actor findById(Short id)throws RuntimeException {

        return actorDao.findById(id).orElseThrow(() -> new RuntimeException("Actor not found"));
    }
    public List<Actor> findAll(){
      return actorDao.findAll();
    }
    public void delete(Short id) {
        EntityManager em= JPAUtil.getEntityManagerFactory().createEntityManager();

            Actor actor=em.find(Actor.class,id);
          if(actor==null)
          {
              throw new RuntimeException("Actor not found");
          }
          actorDao.delete(id);
    }
}
