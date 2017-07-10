package com.bairro.biblioteca.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/info")
public class InfoResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String info() {
		return "Biblioteca API está rodando...";
	}

}
