package ca.sunlife.poc.boogle.exception;

public class BoogleException extends RuntimeException {

	private static final long serialVersionUID = 1153107593727184984L;

	private String errorCode = "";

	public BoogleException(String errorMessage, String errorCode) {
		super(errorMessage);
		this.errorCode = errorCode;

	}
	public String getErrorCode() {
		return errorCode;
	}
}
