package ca.sunlife.poc.boogle.response.sharepoint;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrimaryQueryResult {
	
	@JsonProperty("RelevantResults")
	private RelevantResults relevantResults;
	
	public void setRelevantResults(RelevantResults relevantResults) {
		this.relevantResults = relevantResults;
	}
	
	public RelevantResults getRelevantResults() {
		return relevantResults;
	}
	
	

}
