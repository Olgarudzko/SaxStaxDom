package by.rudzko.web.service;

import javax.servlet.http.HttpServletRequest;

import by.rudzko.web.service.exception.ServiceException;

public interface ParsingService {
		String parseDom(HttpServletRequest request) throws ServiceException;
		String parseSax(HttpServletRequest request) throws ServiceException;
		String parseStax(HttpServletRequest request) throws ServiceException;
}
