package iti.note.controller;

import iti.note.model.Taccuino;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

@Path("/taccuino")
public class TaccuinoController {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response get() {
		return Response.ok(Taccuino.retrieve()).build();
	}
	
	@GET
	@Produces({ MediaType.TEXT_HTML })
	public Response getView() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("taccuini", Taccuino.retrieve());
		
		return Response.ok(new Viewable("/taccuini.jsp", model)).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("search")
	public Response searchByTitolo(@QueryParam("titolo") String titolo) {
		return Response.ok(Taccuino.searchByTitolo(titolo)).build();
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getById(@PathParam("id") long id) {
		return Response.ok(Taccuino.retrieve(id)).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(Taccuino t) {
		t = t.create();
		return Response.ok(t).build();
	}
}
