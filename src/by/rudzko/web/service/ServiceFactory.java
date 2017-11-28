package by.rudzko.web.service;

import by.rudzko.web.service.implementation.ParsingServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final ParsingService parsingService = new ParsingServiceImpl();

    private ServiceFactory() {
    }

    public ParsingService getParsingService() {
        return parsingService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

}
