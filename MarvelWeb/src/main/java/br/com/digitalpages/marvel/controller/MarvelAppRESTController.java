package br.com.digitalpages.marvel.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.digitalpages.marvel.model.Character;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
@Path("/characters.json")
public class MarvelAppRESTController {

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Character> listCharacters() {
		return null;
	}

	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Character findCharacter(@PathParam("id") long idCharacter) {
		return null;
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCharacter(Character character) {
		return null;
	}

	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCharacter(Character character, @PathParam("id") long idNota) {
		return null;
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCharacter(@PathParam("id") long idCharacter) {
		return null;
	}

}
