
package ca.sunlife.poc.boogle.response.sharepoint;


public class Resource {

	private String name;
	private String webUrl;
	private Fields fields;
	private String lastModifiedDateTime;
	
	public String getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public Fields getFields() {
		return fields;
	}
	public void setFields(Fields fields) {
		this.fields = fields;
	}
	
	public void setLastModifiedDateTime(String lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

}
