package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
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

@WebService
public class FilmWebService {

    private FilmService filmService = ServiceFactory.getFilmService();

    @WebMethod
    public ReturnFilm getFilmById(Short id) {
        return filmService.findById(id);
    }

    @WebMethod
    public List<Film> getAllFilms() {
        return filmService.findAll();
    }

    @WebMethod
    public Film getFilmWithLanguage(Short id) {
        return filmService.findWithLanguage(id);
    }

    @WebMethod
    public Film createFilm(CreateFilmRequest request) throws LanguageNotFound {
        Film film = new Film();
        film.setTitle(request.getTitle());
        film.setDescription(request.getDescription());
        film.setReleaseYear(request.getReleaseYear());
        film.setRentalRate(request.getRentalRate());
        film.setLastUpdate(Instant.now());
        film.setRentalDuration((short) 3);
        film.setReplacementCost(new BigDecimal("19.99"));
        film.setRating("G");

        Optional<Language> opt= ServiceFactory.getLanguageService().getById(request.getLanguageId());
        film.setLanguage(opt.get());
        opt.orElseThrow(()-> new LanguageNotFound("Language not found"));

        return filmService.create(film);
    }

    @WebMethod
    public void deleteFilm(Short id) {
        filmService.delete(id);
    }
    @WebMethod
    public void addActorToFilm(AddActorToFilmRequest request)
    {
        filmService.addActorToFilm(request);
    }
}
