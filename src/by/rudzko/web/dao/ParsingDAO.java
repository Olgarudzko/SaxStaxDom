package by.rudzko.web.dao;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import by.rudzko.web.dao.exception.DAOException;

public interface ParsingDAO {

	File provideSource(HttpServletRequest request) throws DAOException;

}
