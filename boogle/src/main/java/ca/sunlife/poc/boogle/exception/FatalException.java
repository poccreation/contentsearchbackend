package ca.sunlife.poc.boogle.exception;

public class FatalException extends RuntimeException {

	private static final long serialVersionUID = 1153107593727184984L;

	private String errorCode = "";

	public FatalException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;

	}
	public FatalException( String errorMessage) {
		super(errorMessage);

	}
	public String getErrorCode() {
		return errorCode;
	}
}
