package ca.sunlife.poc.boogle.response;

public class AccessTokenResponse {

	private String access_token;
	private String expires_in;
	private String token_type;
	private String scope;

	public String getAccess_token() {
		return access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public String getScope() {
		return scope;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

}
