package com.planetclubs.service;

import java.util.List;

import in.planetclubs.TokensType;

public interface ITokensService {

	TokensType getTokenByUserId(int id);

	String createToken(TokensType token);
	
	String updateToken(TokensType token);
	
}
