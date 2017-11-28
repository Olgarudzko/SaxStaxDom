package by.rudzko.web.dao.exception;

public class DAOException extends Exception{
	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Exception exception) {
		super(message, exception);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception exception) {
		super(exception);
	}

	
}
