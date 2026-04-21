package org.iti.soap.factory;

import org.iti.soap.dao.LanguageDao;
import org.iti.soap.service.LanguageService;

public class ServiceFactory {
    private static final LanguageService languageService =
            new LanguageService(new LanguageDao());

    public static LanguageService getLanguageService() {
        return languageService;
    }
}
