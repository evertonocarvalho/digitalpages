package br.com.digitalpages.marvel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalpages.marvel.dao.CharacterRepository;
import br.com.digitalpages.marvel.model.Character;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
@RestController
@RequestMapping("/characters.json")
public class MarvelAppRESTController {

	@Autowired
	private CharacterRepository characterRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public List<Character> listCharacters() throws Exception {
		return characterRepository.findAll(new PageRequest(0, 20, Sort.Direction.ASC, "name")).getContent();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
	public Character findCharacter(@PathVariable("id") long idCharacter) throws Exception {
		return characterRepository.findOne(idCharacter);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public String insertCharacter(@RequestBody Character character) throws Exception {
		characterRepository.save(character);
		return "Character inserted!";
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public String updateCharacter(@RequestBody Character character, @PathVariable("id") long idCharacter) throws Exception {
		character.setId(idCharacter);
		characterRepository.save(character);
		return "Character updated!";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public String deleteCharacter(@PathVariable("id") long idCharacter) throws Exception {
		characterRepository.delete(idCharacter);
		return "Character deleted!";
	}

}
