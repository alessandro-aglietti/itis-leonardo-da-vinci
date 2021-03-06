package iti.note.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello-world-rest")
public class HelloWorldRest {

	@GET
	@Produces({ MediaType.TEXT_HTML })
	public Response get() {

		return Response.ok("hello world").build();
	}
}