package iti.note.controller;

import iti.note.model.Taccuino;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

@Path("/taccuino")
public class TaccuinoController {
	
	@Context
	HttpServletRequest request;
	
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
	
	@GET
	@Path("/{id}/note")
	@Produces({ MediaType.TEXT_HTML })
	public Response getNoteViewByTaccuinoId(@PathParam("id") long id) {
		
		String view = "/taccuini.jsp";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("note", Taccuino.retrieve(id).getNote());
		
		if (request.getHeader("X-PJAX") != null) {
			view = "/note-partial.jsp";
		} else {
			model.put("taccuini", Taccuino.retrieve());
		}
		
		return Response.ok(new Viewable(view, model)).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(Taccuino t) {
		t = t.create();
		return Response.ok(t).build();
	}
}
