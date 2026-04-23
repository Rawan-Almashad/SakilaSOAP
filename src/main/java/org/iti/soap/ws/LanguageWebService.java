package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import org.iti.soap.entity.Language;
import org.iti.soap.exception.LanguageNotFound;
import org.iti.soap.factory.ServiceFactory;
import org.iti.soap.service.LanguageService;
import java.util.List;

@WebService(
        name = "LanguageWebService",
        serviceName = "LanguageWebService",
        targetNamespace = "http://language.ws.soap.iti.org/"
)
public class LanguageWebService {
    private final LanguageService service = ServiceFactory.getLanguageService();

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.GetLanguageById")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.GetLanguageByIdResponse")
    public Language getLanguageById(@WebParam(name = "id") Short id) throws LanguageNotFound {
        return service.getById(id)
                .orElseThrow(() -> new LanguageNotFound("Language is not found"));
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.GetAllLanguages")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.GetAllLanguagesResponse")
    public List<Language> getAllLanguages() {
        return service.getAll();
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.CreateLanguage")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.CreateLanguageResponse")
    public Language createLanguage(@WebParam(name = "name") String name) {
        Language language = new Language();
        language.setName(name);
        return service.create(language);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.DeleteLanguage")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.DeleteLanguageResponse")
    public boolean deleteLanguage(@WebParam(name = "id") short id) {
        return service.delete(id);
    }
}