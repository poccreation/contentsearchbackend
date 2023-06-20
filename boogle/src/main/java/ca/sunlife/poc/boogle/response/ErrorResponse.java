package ca.sunlife.poc.boogle.response;

public class ErrorResponse {

	private String code;
	private String message;

	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ ");
		buffer.append("code:");
		buffer.append(getCode());
		buffer.append("message:");
		buffer.append(getMessage());
		buffer.append(" ]");
		return buffer.toString();
	}

}
