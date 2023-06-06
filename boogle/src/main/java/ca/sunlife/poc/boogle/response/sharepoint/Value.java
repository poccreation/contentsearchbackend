package ca.sunlife.poc.boogle.response.sharepoint;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Value {
	@JsonProperty("hitsContainers")
	   List<HitsContainers> hitsContainers;
	
	public void setHitsContainers(List<HitsContainers> hitsContainers) {
		this.hitsContainers = hitsContainers;
	}
	
	public List<HitsContainers> getHitsContainers() {
		return hitsContainers;
	}
}
