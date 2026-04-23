package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
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
        targetNamespace = "http://country.ws.soap.iti.org/"
)
public class CountryWebService {
    private CountryService countryService = ServiceFactory.getCountryService();

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindByIdCountry")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindByIdCountryResponse")
    public Country findById(@WebParam(name = "id") Short id) throws RuntimeException {
        return countryService.findById(id);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindAllCountries")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindAllCountriesResponse")
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.SaveCountry")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.SaveCountryResponse")
    public CreateCountry save(@WebParam(name = "request") CreateCountry request) {
        return countryService.save(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.UpdateCountry")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.UpdateCountryResponse")
    public CreateCountry update(@WebParam(name = "request") UpdateCountryRequest request) {
        return countryService.update(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.DeleteByIdCountry")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.DeleteByIdCountryResponse")
    public boolean deleteById(@WebParam(name = "id") Short id) {
        return countryService.deleteById(id);
    }
}