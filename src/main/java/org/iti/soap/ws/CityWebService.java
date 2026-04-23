package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import org.iti.soap.dto.CreateCityRequest;
import org.iti.soap.dto.UpdateCityRequest;
import org.iti.soap.entity.City;
import org.iti.soap.factory.ServiceFactory;
import org.iti.soap.service.CityService;
import java.util.List;

@WebService(
        name = "CityWebService",
        serviceName = "CityWebService",
        portName = "CityWebServicePort",
        targetNamespace = "http://city.ws.soap.iti.org/"
)
public class CityWebService {
    private CityService cityService = ServiceFactory.getCityService();

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindByIdCity")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindByIdCityResponse")
    public CreateCityRequest findById(@WebParam(name = "id") Short id) {
        City city = cityService.findById(id);
        CreateCityRequest returnedCity = new CreateCityRequest();
        returnedCity.setCity(city.getCity());
        returnedCity.setCountryId(city.getId());
        return returnedCity;
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindAllCities")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindAllCitiesResponse")
    public List<City> findAll() {
        return cityService.findAll();
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.SaveCity")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.SaveCityResponse")
    public CreateCityRequest save(@WebParam(name = "request") CreateCityRequest request) {
        return cityService.save(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.UpdateCity")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.UpdateCityResponse")
    public UpdateCityRequest update(@WebParam(name = "request") UpdateCityRequest request) {
        return cityService.update(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.DeleteByIdCity")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.DeleteByIdCityResponse")
    public boolean deleteById(@WebParam(name = "id") Short id) {
        return cityService.deleteById(id);
    }
}