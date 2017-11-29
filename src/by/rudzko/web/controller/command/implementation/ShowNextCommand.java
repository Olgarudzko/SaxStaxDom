package by.rudzko.web.controller.command.implementation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.rudzko.web.controller.Logger;
import by.rudzko.web.controller.command.Command;
import by.rudzko.web.service.ServiceFactory;
import by.rudzko.web.service.exception.ServiceException;

public class ShowNextCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory factory = ServiceFactory.getInstance();
    	List<String> result = null;
    	try {
			result = factory.getParsingService().showNext();
    	} catch (ServiceException e) {
			Logger.getLogger().printError(request, e);
		}
    	request.setAttribute(DomParserCommand.RESULT, result);

	}

}
