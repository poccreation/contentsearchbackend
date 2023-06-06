package ca.sunlife.poc.boogle.confluence.service;

import ca.sunlife.poc.boogle.exception.FatalException;
import ca.sunlife.poc.boogle.exception.BoogleException;
import ca.sunlife.poc.boogle.response.AccessTokenResponse;

public interface IConfluenceAuthService {

	public AccessTokenResponse fetchOAuthToken() throws FatalException, BoogleException;

}
