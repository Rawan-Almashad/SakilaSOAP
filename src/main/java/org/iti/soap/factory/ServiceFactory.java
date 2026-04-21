package org.iti.soap.factory;

import org.iti.soap.dao.FilmDao;
import org.iti.soap.dao.LanguageDao;
import org.iti.soap.service.FilmService;
import org.iti.soap.service.LanguageService;

public class ServiceFactory {
    private static final LanguageService languageService =
            new LanguageService(new LanguageDao());
    private static final FilmService filmService =
            new FilmService(new FilmDao());

    public static LanguageService getLanguageService() {
        return languageService;
    }
    public static FilmService getFilmService() {
        return filmService;
    }
}
