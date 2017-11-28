package by.rudzko.web.dao;

import by.rudzko.web.dao.implementation.ParsingDAOimpl;

public final class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final ParsingDAO userDao = new ParsingDAOimpl();

    private DAOFactory() {
    }

    public ParsingDAO getParsingDAO() {
        return userDao;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}
