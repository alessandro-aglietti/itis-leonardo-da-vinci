package iti.note.controller;

import iti.note.model.Taccuino;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/taccuino")
public class TaccuinoController {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response get() {
		return Response.ok(Taccuino.retrieve()).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("search")
	public Response searchByTitolo(@QueryParam("titolo") String titolo) {
		return Response.ok(Taccuino.searchByTitolo(titolo)).build();
	}
}
