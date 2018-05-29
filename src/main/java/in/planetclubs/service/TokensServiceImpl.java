package com.planetclubs.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.TokensDAO;
import com.planetclubs.model.Membership;
import com.planetclubs.model.Notifications;
import com.planetclubs.model.Tokens;

import in.planetclubs.NotificationsListType;
import in.planetclubs.NotificationsType;
import in.planetclubs.TokensType;

@Component
@Path("/tokensservice")
public class TokensServiceImpl implements ITokensService{
	
	@Autowired
	private TokensDAO tokensDAO;

	@GET
	@Path("gettokenbyuserid/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public TokensType getTokenByUserId(@PathParam("id") int id) {
		Tokens token = tokensDAO.getTokenByUserId("userId",id);

		TokensType tokensType = new TokensType();

		tokensType.setToken(token.getToken());
		tokensType.setUserId(token.getUserId());
		
		return tokensType;
	}

	@POST
	@Path("createtoken")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String createToken(TokensType tokenType) {
		Tokens token = new Tokens();
		token.setToken(tokenType.getToken());
		token.setUserId(tokenType.getUserId());		
		
		return tokensDAO.createToken(token);
	}

	@POST
	@Path("updatetoken")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateToken(TokensType tokenType) {
		Tokens token = new Tokens();
		token.setToken(tokenType.getToken());
		token.setUserId(tokenType.getUserId());		
		
		return tokensDAO.updateToken(token);
	}

}
