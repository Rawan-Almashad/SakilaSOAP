package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import org.iti.soap.dto.CreateCustomerRequest;
import org.iti.soap.dto.CustomerResponse;
import org.iti.soap.dto.UpdateCustomerRequest;
import org.iti.soap.factory.ServiceFactory;
import org.iti.soap.service.CustomerService;
import java.util.List;

@WebService(
        name = "CustomerWebService",
        serviceName = "CustomerWebService",
        targetNamespace = "http://customer.ws.soap.iti.org/"
)
public class CustomerWebService {
    private CustomerService customerService = ServiceFactory.getCustomerService();

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.CreateCustomer")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.CreateCustomerResponse")
    public CustomerResponse create(@WebParam(name = "request") CreateCustomerRequest request) {
        return customerService.create(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindByIdCustomer")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindByIdCustomerResponse")
    public CustomerResponse findById(@WebParam(name = "id") Short id) {
        return customerService.findById(id);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindAllCustomers")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindAllCustomersResponse")
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.UpdateCustomer")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.UpdateCustomerResponse")
    public CustomerResponse update(@WebParam(name = "request") UpdateCustomerRequest request) {
        return customerService.update(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.DeleteCustomer")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.DeleteCustomerResponse")
    public boolean delete(@WebParam(name = "id") Short id) {
        return customerService.deleteById(id);
    }
}