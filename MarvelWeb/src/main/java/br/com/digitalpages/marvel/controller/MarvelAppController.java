package br.com.digitalpages.marvel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.digitalpages.marvel.dao.CharacterRepository;
import br.com.digitalpages.marvel.model.Character;
import br.com.digitalpages.marvel.model.Login;
import br.com.digitalpages.marvel.model.PaginatedList;
import br.com.digitalpages.marvel.service.MarvelAPIService;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
@Controller
@RequestMapping("/MarvelWeb")
public class MarvelAppController {

	public static final String LOGIN_SESSION_KEY = "login";

	@Autowired
	private MarvelAPIService marvelAPIService;

	@Autowired
	private CharacterRepository characterRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initLogin() {
		ModelAndView model = new ModelAndView("marvel/login");
		model.addObject("login", new Login());
		return model;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ModelAndView login(Login login, HttpSession httpSession) throws Exception {
		httpSession.setAttribute(LOGIN_SESSION_KEY, login);
		marvelAPIService.consumeAPIAndSaveCharacters(login);
		return listCharacters(0);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listCharacters")
	public ModelAndView listCharacters(@RequestParam("page") int page) throws Exception {
		Page<Character> pageCharacters = characterRepository.findAll(new PageRequest(page, 20, Sort.Direction.ASC, "name"));
		return new ModelAndView("marvel/list").addObject("paginatedList", new PaginatedList(pageCharacters.getContent(), 1485));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/characterDetail/{page}/{id}")
	public ModelAndView characterDetail(@PathVariable("page") int page, @PathVariable("id") Long idCharacter, HttpSession httpSession) throws Exception {
		ModelAndView modelAndView = new ModelAndView("marvel/characterDetail");

		modelAndView.addObject("character", characterRepository.findOne(idCharacter));
		modelAndView.addObject("characterComics", marvelAPIService.listCharacterComics((Login) httpSession.getAttribute(LOGIN_SESSION_KEY), idCharacter));
		modelAndView.addObject("backTo", page);

		return modelAndView;
	}

}
