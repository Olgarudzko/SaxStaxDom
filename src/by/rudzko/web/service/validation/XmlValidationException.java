package by.rudzko.web.service.validation;

public class XmlValidationException extends Exception{

	private static final long serialVersionUID = 3944893140698206860L;
	
	public XmlValidationException() {
		super();
	}

	public XmlValidationException(String message) {
        super(message);
    }
	
	public XmlValidationException(String message, Exception exception) {
        super(message, exception);
    }
	
	public XmlValidationException(Exception exception) {
        super(exception);
    }
}
