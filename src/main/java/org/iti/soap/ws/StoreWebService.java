package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import org.iti.soap.dto.CreateStoreRequest;
import org.iti.soap.dto.StoreResponse;
import org.iti.soap.dto.UpdateStoreRequest;
import org.iti.soap.factory.ServiceFactory;
import org.iti.soap.service.StoreService;
import java.util.List;

@WebService(
        name = "StoreWebService",
        serviceName = "StoreWebService",
        targetNamespace = "http://store.ws.soap.iti.org/"
)
public class StoreWebService {
    private StoreService storeService = ServiceFactory.getStoreService();

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.CreateStore")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.CreateStoreResponse")
    public StoreResponse create(@WebParam(name = "request") CreateStoreRequest request) {
        return storeService.create(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindByIdStore")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindByIdStoreResponse")
    public StoreResponse findById(@WebParam(name = "id") Short id) {
        return storeService.findById(id);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindAllStores")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindAllStoresResponse")
    public List<StoreResponse> findAll() {
        return storeService.findAll();
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.UpdateStore")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.UpdateStoreResponse")
    public StoreResponse update(@WebParam(name = "request") UpdateStoreRequest request) {
        return storeService.update(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.DeleteStore")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.DeleteStoreResponse")
    public boolean delete(@WebParam(name = "id") Short id) {
        return storeService.deleteById(id);
    }
}