package ca.sunlife.poc.boogle.response.sharepoint;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SharepointResponse {
	
	@JsonProperty("PrimaryQueryResult")
	private PrimaryQueryResult primaryQueryResult;
	
	public void setPrimaryQueryResult(PrimaryQueryResult primaryQueryResult) {
		this.primaryQueryResult = primaryQueryResult;
	}
	public PrimaryQueryResult getPrimaryQueryResult() {
		return primaryQueryResult;
	}
}
