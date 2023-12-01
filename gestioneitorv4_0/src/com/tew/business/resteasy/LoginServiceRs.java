package com.tew.business.resteasy;


@Path("/LoginServiceRs")
public interface LoginServiceRs {
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.TEXT_PLAIN })
	String login(User user);
}
