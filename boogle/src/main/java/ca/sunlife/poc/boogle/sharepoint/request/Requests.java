package ca.sunlife.poc.boogle.sharepoint.request;

import java.util.List;


public class Requests {
	private List<String> entityTypes;

    private Query query;

    private String region;

    private long from;

    private long size;

    private List<String> fields;

	public List<String> getEntityTypes() {
		return entityTypes;
	}

	public void setEntityTypes(List<String> entityTypes) {
		this.entityTypes = entityTypes;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	

	public long getFrom() {
		return from;
	}

	public void setFrom(long from) {
		this.from = from;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

    
}
