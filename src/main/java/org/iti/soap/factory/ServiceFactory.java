package org.iti.soap.factory;

import org.iti.soap.dao.ActorDao;
import org.iti.soap.dao.CountryDao;
import org.iti.soap.dao.FilmDao;
import org.iti.soap.dao.LanguageDao;
import org.iti.soap.service.ActorService;
import org.iti.soap.service.CountryService;
import org.iti.soap.service.FilmService;
import org.iti.soap.service.LanguageService;

public class ServiceFactory {
    private static final LanguageService languageService =
            new LanguageService(new LanguageDao());
    private static final FilmService filmService =
            new FilmService(new FilmDao());
    private static final ActorService actorService =
            new ActorService(new ActorDao());
    private static final CountryService countryService =
            new CountryService(new CountryDao());

    public static LanguageService getLanguageService() {
        return languageService;
    }
    public static FilmService getFilmService() {
        return filmService;
    }
    public static ActorService getActorService(){return actorService;}
    public static CountryService getCountryService(){return countryService;}
}
