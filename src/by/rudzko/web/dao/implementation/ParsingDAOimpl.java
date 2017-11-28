package by.rudzko.web.dao.implementation;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import by.rudzko.web.dao.ParsingDAO;
import by.rudzko.web.dao.exception.DAOException;

public class ParsingDAOimpl implements ParsingDAO{
	private static final String WEB_INF = "/WEB-INF";
	private static final String EXAMPLE = "/files/example.xml";
    
	@Override
	public File provideSource(HttpServletRequest request) throws DAOException {
		return new File(request.getServletContext().getRealPath(WEB_INF).concat(EXAMPLE));
	}
}
