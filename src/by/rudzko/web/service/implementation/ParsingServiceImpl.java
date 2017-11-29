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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

public class ParsingServiceImpl implements ParsingService{
	
	private static final String LINE_BRAKER = "[\n]+";
	ParsingDAO parsingDAO = DAOFactory.getInstance().getParsingDAO();
	File source;
	List<String> result;
	int outputPosition;
	
	@Override
	public List<String> parseDom(HttpServletRequest request) throws ServiceException {
		try {
			source = parsingDAO.provideSource(request);
			Validator.isXmlValid(source);
			DocumentBuilder builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Element element=builder.parse(source).getDocumentElement();
            DomParser.parseDom(element);
            result = removeEmptyCells(DomParser.getParsedData().toString().trim().split(LINE_BRAKER));
		} catch (DAOException | ParserConfigurationException | SAXException
				| IOException | XmlValidationException e) {
			throw new ServiceException(e);
		}
		List <String> tags = new ArrayList<>();
		for (int i=0; i<result.size(); i++) {
			String tag = result.get(i);
			if (!tags.contains(tag)) {
				tags.add(tag);
			} else {
				outputPosition = i;
				return result.subList(0, i);
			}
		}
		return Collections.<String>emptyList();
	}
	
	@Override
	public List<String> parseSax(HttpServletRequest request) throws ServiceException {
		try {
			source = parsingDAO.provideSource(request);
			Validator.isXmlValid(source);
			SAXParserFactory create = SAXParserFactory.newInstance();
            SAXParser parser = create.newSAXParser();
            SaxParser sax = new SaxParser();
            parser.parse(source, sax);
            result=removeEmptyCells(sax.getParsedData().toString().trim().split(LINE_BRAKER));
		} catch (DAOException | ParserConfigurationException | SAXException
				| IOException | XmlValidationException e) {
			throw new ServiceException(e);
		}
		List <String> tags = new ArrayList<>();
		for (int i=0; i<result.size(); i++) {
			String tag = result.get(i);
			if (!tags.contains(tag)) {
				tags.add(tag);
			} else {
				outputPosition = i;
				return result.subList(0, i);
			}
		}
		return Collections.<String>emptyList();
	}
	
	@Override
	public List<String> parseStax(HttpServletRequest request) throws ServiceException {
		try {
			source = parsingDAO.provideSource(request);
			Validator.isXmlValid(source);
			StaxParser.parseStax(source);
			result=removeEmptyCells(StaxParser.getParsedData().toString().trim().split(LINE_BRAKER));
		} catch (DAOException | XmlValidationException e) {
			throw new ServiceException(e);
		} 		
		List <String> tags = new ArrayList<>();
		for (int i=0; i<result.size(); i++) {
			String tag = result.get(i);
			if (!tags.contains(tag)) {
				tags.add(tag);
			} else {
				outputPosition = i;
				return result.subList(0, i);
			}
		}
		return Collections.<String>emptyList();
	}

	@Override
	public List<String> showNext() throws ServiceException {
		int start = outputPosition;
		List <String> tags = new ArrayList<>();
		for (int i=start; i<result.size(); i++) {
			String tag = result.get(i);
			if (!tags.contains(tag)) {
				tags.add(tag);
			} else {
				outputPosition = i;
				return result.subList(start, i);
			}
		}
		return Collections.<String>emptyList();
	}

	private List<String> removeEmptyCells(String[] array) {
		List <String> lines = new ArrayList<>(); 
		for (String line: array) {
			if (!line.trim().isEmpty()) {
				lines.add(line);
			}
		}
		return lines;
	}

}
