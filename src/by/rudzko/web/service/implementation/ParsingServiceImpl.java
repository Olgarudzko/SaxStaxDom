package by.rudzko.web.service.implementation;

import by.rudzko.web.dao.DAOFactory;
import by.rudzko.web.dao.ParsingDAO;
import by.rudzko.web.dao.exception.DAOException;
import by.rudzko.web.service.ParsingService;
import by.rudzko.web.service.exception.ServiceException;
import by.rudzko.web.service.parser.DomParser;
import by.rudzko.web.service.parser.SaxParser;
import by.rudzko.web.service.parser.StaxParser;
import by.rudzko.web.service.validation.Validator;
import by.rudzko.web.service.validation.XmlValidationException;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

public class ParsingServiceImpl implements ParsingService{
	
	ParsingDAO parsingDAO = DAOFactory.getInstance().getParsingDAO();
	File source=null;
	String result=null;
	
	@Override
	public String parseDom(HttpServletRequest request) throws ServiceException {
		try {
			source = parsingDAO.provideSource(request);
			Validator.isXmlValid(source);
			DocumentBuilder builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Element element=builder.parse(source).getDocumentElement();
            DomParser.parseDom(element);
            result=DomParser.getParsedData().toString();
		} catch (DAOException | ParserConfigurationException | SAXException
				| IOException | XmlValidationException e) {
			throw new ServiceException(e);
		}
		return result;
	}
	
	@Override
	public String parseSax(HttpServletRequest request) throws ServiceException {
		try {
			source = parsingDAO.provideSource(request);
			Validator.isXmlValid(source);
			SAXParserFactory create = SAXParserFactory.newInstance();
            SAXParser parser = create.newSAXParser();
            SaxParser sax = new SaxParser();
            parser.parse(source, sax);
            result=sax.getParsedData().toString();
		} catch (DAOException | ParserConfigurationException | SAXException
				| IOException | XmlValidationException e) {
			throw new ServiceException(e);
		}
		return result;
	}
	
	@Override
	public String parseStax(HttpServletRequest request) throws ServiceException {
		try {
			source = parsingDAO.provideSource(request);
			Validator.isXmlValid(source);
			StaxParser.parseStax(source);
			result=StaxParser.getParsedData().toString();
		} catch (DAOException | XmlValidationException e) {
			throw new ServiceException(e);
		} 		
		return result;
	}

}
