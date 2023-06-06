package ca.sunlife.poc.boogle.response.sharepoint;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hits {

	   @JsonProperty("hitId")
	   String hitId;

	   @JsonProperty("rank")
	   int rank;

	   @JsonProperty("summary")
	   String summary;

	   @JsonProperty("resource")
	   Resource resource;

	public String getHitId() {
		return hitId;
	}

	public void setHitId(String hitId) {
		this.hitId = hitId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	   

}
