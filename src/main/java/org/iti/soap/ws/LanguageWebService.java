package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.iti.soap.entity.Language;
import org.iti.soap.exception.LanguageNotFound;
import org.iti.soap.factory.ServiceFactory;
import org.iti.soap.service.LanguageService;

import java.util.List;

@WebService
public class LanguageWebService {

    private final LanguageService service =
            ServiceFactory.getLanguageService();

    @WebMethod
    public Language getLanguageById(@WebParam(name = "Id")short id) throws LanguageNotFound {
        return service.getById(id)
                .orElseThrow(() -> new LanguageNotFound("Language is not found"));
    }

    @WebMethod
    public List<Language> getAllLanguages() {
        return service.getAll();
    }

    @WebMethod
    public Language createLanguage( @WebParam(name = "LanguageName")String name) {
        Language language = new Language();
        language.setName(name);
        return service.create(language);
    }

    @WebMethod
    public boolean deleteLanguage(@WebParam(name = "Id")short id) {
        return service.delete(id);
    }
}