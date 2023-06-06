package ca.sunlife.poc.boogle.response.sharepoint;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GraphqlResponse {
	@JsonProperty("value")
	List<Value> value;

	public void setValue(List<Value> value) {
		this.value = value;
	}

	public List<Value> getValue() {
		return value;
	}

}
