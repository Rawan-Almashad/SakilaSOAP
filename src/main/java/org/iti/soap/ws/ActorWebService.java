package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import org.iti.soap.dto.CreateActorRequest;
import org.iti.soap.entity.Actor;
import org.iti.soap.factory.ServiceFactory;
import org.iti.soap.service.ActorService;
import java.util.List;

@WebService(
        name = "ActorWebService",
        serviceName = "ActorWebService",
        targetNamespace = "http://actor.ws.soap.iti.org/"
)
public class ActorWebService {
    private ActorService actorService = ServiceFactory.getActorService();

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.CreateActor")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.CreateActorResponse")
    public Actor create(@WebParam(name = "request") CreateActorRequest request) {
        return actorService.create(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindByIdActor")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindByIdActorResponse")
    public Actor findById(@WebParam(name = "id") Short id) throws RuntimeException {
        return actorService.findById(id);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindAllActors")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindAllActorsResponse")
    public List<Actor> findAll() {
        return actorService.findAll();
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.DeleteActor")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.DeleteActorResponse")
    public void delete(@WebParam(name = "id") Short id) {
        actorService.delete(id);
    }
}