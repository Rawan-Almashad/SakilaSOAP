package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import org.iti.soap.config.JPAUtil;
import org.iti.soap.dao.ActorDao;
import org.iti.soap.dto.CreateActorRequest;
import org.iti.soap.entity.Actor;
import org.iti.soap.factory.ServiceFactory;
import org.iti.soap.service.ActorService;

import java.time.Instant;
import java.util.List;

@WebService
public class ActorWebService {
    private ActorService actorService = ServiceFactory.getActorService();

    @WebMethod
    public Actor create(CreateActorRequest request)
    {

        return actorService.create(request);
    }
    @WebMethod
    public Actor findById(@WebParam(name = "Id")Short id)throws RuntimeException {

        return actorService.findById(id);
    }
    @WebMethod
    public List<Actor> findAll(){
        return actorService.findAll();
    }
    @WebMethod
    public void delete(@WebParam(name = "Id")Short id) {
        actorService.delete(id);
    }
}
