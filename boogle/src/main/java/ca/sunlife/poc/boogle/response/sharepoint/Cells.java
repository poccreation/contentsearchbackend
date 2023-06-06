package ca.sunlife.poc.boogle.response.sharepoint;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cells {
	@JsonProperty("Key")
	private String key;
	@JsonProperty("Value")
	private String value;
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}

}
