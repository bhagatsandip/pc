package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.Tokens;

public interface TokensDAO {

	Tokens getTokenByUserId(String field, int id);

	String createToken(Tokens token);
	
	String updateToken(Tokens token);
	
}
