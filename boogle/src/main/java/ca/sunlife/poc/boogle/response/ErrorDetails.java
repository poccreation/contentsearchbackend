package ca.sunlife.poc.boogle.response;

public class ErrorDetails {

	private String errorCode;
	private String errorDescription;

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ ");
		buffer.append("errorCode:");
		buffer.append(getErrorCode());
		buffer.append("errorDescription:");
		buffer.append(getErrorDescription());
		buffer.append(" ]");
		return buffer.toString();
	}

}
