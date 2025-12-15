package com.production.dto;

public class TokenResponse {
    private String token;
    
	public TokenResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
}