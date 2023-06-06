package ca.sunlife.poc.boogle.response;

public class ResponseDto<T> {

	private T response;
	private String status;
	private ErrorDetails errorDetails;

	public void setResponse(T response) {
		this.response = response;
	}
	
	public T getResponse() {
		return response;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public void setErrorDetails(ErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}

	public ErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public String getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ ");
		buffer.append("status:");
		buffer.append(getStatus());
		buffer.append("errorDetails:");
		buffer.append(getErrorDetails());
		buffer.append("response:");
		buffer.append(getResponse());
		buffer.append(" ]");
		return buffer.toString();
	}

}
