package br.com.digitalpages.marvel.controller;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.digitalpages.marvel.dao.CharacterRepository;
import br.com.digitalpages.marvel.model.Character;
import br.com.digitalpages.marvel.model.CharacterResponse;
import br.com.digitalpages.marvel.model.ComicResponse;
import br.com.digitalpages.marvel.model.Login;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
@Controller
@RequestMapping("/MarvelWeb")
public class MarvelAppController {

	public static final String LOGIN_SESSION_KEY = "login";
	public static final String MARVEL_API_CHARACTERS_URL = "https://gateway.marvel.com/v1/public/characters";
	public static final String MARVEL_API_CHARACTER_COMICS_URL = "http://gateway.marvel.com/v1/public/characters/{characterId}/comics";

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

		// TODO Create a service to consume the characters from the API.
		CharacterResponse response = new RestTemplate().getForObject("https://gateway.marvel.com/v1/public/characters" + this.generateURLParams(login), CharacterResponse.class);

		characterRepository.save(response.getData().getResults());
		return this.listCharacters();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/characterDetail/{id}")
	public ModelAndView characterDetail(@PathVariable("id") Long id, HttpSession httpSession) throws Exception {
		String url = MARVEL_API_CHARACTER_COMICS_URL.replace("{characterId}", id.toString());

		// TODO Create a service to consume the comics from the API.
		ComicResponse comicResponse = new RestTemplate().getForObject(url + this.generateURLParams((Login) httpSession.getAttribute(LOGIN_SESSION_KEY)) + "&format=comic", ComicResponse.class);

		ModelAndView modelAndView = new ModelAndView("marvel/characterDetail");

		modelAndView.addObject("character", characterRepository.findOne(id));
		modelAndView.addObject("characterComics", comicResponse.getData().getResults());

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/backFromCharacterDetail")
	public ModelAndView backFromCharacterDetail(HttpSession httpSession) throws Exception {
		Login loginSession = (Login) httpSession.getAttribute("login");
		if (loginSession == null) {
			return this.initLogin();
		}
		return this.listCharacters();
	}

	@RequestMapping("/listCharacters")
	public ModelAndView listCharacters() throws Exception {
		Iterable<Character> iterable = characterRepository.findAll();
		ArrayList<Character> list = new ArrayList<Character>();
		for (Character character : iterable) {
			list.add(character);
		}
		Collections.sort(list);
		return new ModelAndView("marvel/list").addObject("listCharacters", list);
	}

	private String generateURLParams(Login login) throws Exception {
		Long timestamp = new Date().getTime();
		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		byte[] bytes = md5Digest.digest((timestamp + login.getPrivateKey() + login.getPublicKey()).getBytes("UTF-8"));
		String hash = MD5Encoder.encode(bytes);
		return "?apikey=" + login.getPublicKey() + "&ts=" + timestamp + "&hash=" + hash + "&limit=100";
	}

}
