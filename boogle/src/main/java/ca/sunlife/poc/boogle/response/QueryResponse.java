package ca.sunlife.poc.boogle.response;

public class QueryResponse {
	
	private String title;
	private String path;
	private String fileType;
	private boolean isDocument;
	private String summary;
	private String parentPageName;
	private String parentPagePath;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public void setDocument(boolean isDocument) {
		this.isDocument = isDocument;
	}
	public boolean isDocument() {
		return isDocument;
	}
	public String getSummary() {
		return summary;
	}
	
	public String getParentPageName() {
		return parentPageName;
	}
	
	public String getParentPagePath() {
		return parentPagePath;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public void setParentPageName(String parentPageName) {
		this.parentPageName = parentPageName;
	}
	
	public void setParentPagePath(String parentPagePath) {
		this.parentPagePath = parentPagePath;
	}
	
	
	
	

}
