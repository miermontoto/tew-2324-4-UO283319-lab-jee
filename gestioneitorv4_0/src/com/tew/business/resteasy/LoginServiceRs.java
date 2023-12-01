package com.tew.business.resteasy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tew.model.User;

@Path("/LoginServiceRs")
public interface LoginServiceRs {
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.TEXT_PLAIN })
	String login(User user);
}
