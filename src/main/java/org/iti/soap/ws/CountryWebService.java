package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.iti.soap.dto.CreateCountry;
import org.iti.soap.dto.UpdateCountryRequest;
import org.iti.soap.entity.Country;
import org.iti.soap.factory.ServiceFactory;

import org.iti.soap.service.CountryService;

import java.util.List;
@WebService(
        name = "CountryWebService",
        serviceName = "CountryWebService",
        portName = "CountryWebServicePort",
        targetNamespace = "http://ws.soap.iti.org/"
)
public class CountryWebService {
    private CountryService countryService = ServiceFactory.getCountryService();
@WebMethod
    public Country findById(Short id) throws RuntimeException{
        return countryService.findById(id);
    }
    @WebMethod

    public List<Country> findAll() {
        return countryService.findAll();
    }
    @WebMethod

    public CreateCountry save(CreateCountry request) {
        return countryService.save(request);
    }
    @WebMethod

    public CreateCountry update(UpdateCountryRequest request) {
        return countryService.update(request);
    }
    @WebMethod
    public boolean deleteById(Short id) {
        return countryService.deleteById(id);
    }
}
