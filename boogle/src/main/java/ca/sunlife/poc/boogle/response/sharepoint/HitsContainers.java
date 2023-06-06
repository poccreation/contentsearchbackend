package ca.sunlife.poc.boogle.response.sharepoint;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HitsContainers {
	   @JsonProperty("hits")
	   List<Hits> hits;

	   @JsonProperty("total")
	   int total;

	   @JsonProperty("moreResultsAvailable")
	   boolean moreResultsAvailable;

	public List<Hits> getHits() {
		return hits;
	}

	public void setHits(List<Hits> hits) {
		this.hits = hits;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isMoreResultsAvailable() {
		return moreResultsAvailable;
	}

	public void setMoreResultsAvailable(boolean moreResultsAvailable) {
		this.moreResultsAvailable = moreResultsAvailable;
	}
	   
	   

}
