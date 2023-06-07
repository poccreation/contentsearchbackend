package ca.sunlife.poc.boogle.response.confluence;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Results {
	
	   @JsonProperty("id")
	  private  String id;

	   @JsonProperty("type")
	   private String type;

	   @JsonProperty("status")
	   private  String status;

	   @JsonProperty("title")
	   private String title;

	   @JsonProperty("space")
	   private Space space;
	   
	   @JsonProperty("version")
	   private Version version;
	   @JsonProperty("body")
	   private Body body;
	   @JsonProperty("_links")
	   private Links links;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Space getSpace() {
		return space;
	}
	public void setSpace(Space space) {
		this.space = space;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	public Links getLinks() {
		return links;
	}
	public void setLinks(Links links) {
		this.links = links;
	}

	public Version getVersion() {
		return version;
	}
	public void setVersion(Version version) {
		this.version = version;
	}
	   
}
