package by.rudzko.web.controller.command.implementation;

import by.rudzko.web.controller.Logger;
import by.rudzko.web.controller.command.Command;
import by.rudzko.web.service.ServiceFactory;
import by.rudzko.web.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DomParserCommand implements Command {
	private static final String RESULT="result";
	
    public void execute(HttpServletRequest request, HttpServletResponse response) {
    	ServiceFactory factory = ServiceFactory.getInstance();
    	String [] result = null;
    	try {
			String parsed =factory.getParsingService().parseDom(request);
			result = parsed.trim().split("\n");
		} catch (ServiceException e) {
			Logger.getLogger().printError(request, e);
		}
        request.setAttribute(RESULT, result);
    }
}
