package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import org.iti.soap.dto.AddActorToFilmRequest;
import org.iti.soap.dto.CreateFilmRequest;
import org.iti.soap.dto.ReturnFilm;
import org.iti.soap.entity.Film;
import org.iti.soap.entity.Language;
import org.iti.soap.exception.LanguageNotFound;
import org.iti.soap.factory.ServiceFactory;
import org.iti.soap.service.FilmService;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@WebService(
        name = "FilmWebService",
        serviceName = "FilmWebService",
        targetNamespace = "http://film.ws.soap.iti.org/"
)
public class FilmWebService {
    private FilmService filmService = ServiceFactory.getFilmService();

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.GetFilmById")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.GetFilmByIdResponse")
    public ReturnFilm getFilmById(@WebParam(name = "id") Short id) {
        return filmService.findById(id);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.GetAllFilms")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.GetAllFilmsResponse")
    public List<Film> getAllFilms() {
        return filmService.findAll();
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.GetFilmWithLanguage")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.GetFilmWithLanguageResponse")
    public Film getFilmWithLanguage(@WebParam(name = "id") Short id) {
        return filmService.findWithLanguage(id);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.CreateFilm")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.CreateFilmResponse")
    public Film createFilm(@WebParam(name = "request") CreateFilmRequest request) throws LanguageNotFound {
        Film film = new Film();
        film.setTitle(request.getTitle());
        film.setDescription(request.getDescription());
        film.setReleaseYear(request.getReleaseYear());
        film.setRentalRate(request.getRentalRate());
        film.setLastUpdate(Instant.now());
        film.setRentalDuration((short) 3);
        film.setReplacementCost(new BigDecimal("19.99"));
        film.setRating("G");
        Optional<Language> opt = ServiceFactory.getLanguageService().getById(request.getLanguageId());
        film.setLanguage(opt.get());
        opt.orElseThrow(() -> new LanguageNotFound("Language not found"));
        return filmService.create(film);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.DeleteFilm")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.DeleteFilmResponse")
    public void deleteFilm(@WebParam(name = "id") Short id) {
        filmService.delete(id);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.AddActorToFilm")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.AddActorToFilmResponse")
    public void addActorToFilm(@WebParam(name = "request") AddActorToFilmRequest request) {
        filmService.addActorToFilm(request);
    }
}