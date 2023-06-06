package ca.sunlife.poc.boogle.sharepoint.request;

import java.util.ArrayList;
import java.util.List;

public class GraphqlRequest {

	public GraphqlRequest() {
		if (null == requests) {
			this.requests = new ArrayList<>();
		}
	}

	private List<Requests> requests;
	
	public List<Requests> getRequests() {
		return this.requests;
	}

}
