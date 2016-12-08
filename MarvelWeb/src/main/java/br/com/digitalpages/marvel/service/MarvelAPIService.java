package br.com.digitalpages.marvel.service;

import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.digitalpages.marvel.dao.CharacterRepository;
import br.com.digitalpages.marvel.model.CharacterResponse;
import br.com.digitalpages.marvel.model.Comic;
import br.com.digitalpages.marvel.model.ComicResponse;
import br.com.digitalpages.marvel.model.Login;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
@Service
public class MarvelAPIService {

	@Autowired
	private CharacterRepository characterRepository;

	public static final String MARVEL_API_CHARACTERS_URL = "https://gateway.marvel.com/v1/public/characters";
	public static final String MARVEL_API_CHARACTER_COMICS_URL = "https://gateway.marvel.com/v1/public/characters/{characterId}/comics";

	public void consumeAPIAndSaveCharacters(Login login) throws Exception {
		if (characterRepository.count() <= 0) {
			CharacterResponse response = new RestTemplate().getForObject(MARVEL_API_CHARACTERS_URL + generateURLParams(login) + "&limit=100", CharacterResponse.class);
			characterRepository.save(response.getData().getResults());
		}
	}

	public List<Comic> listCharacterComics(Login login, Long idCharacter) throws Exception {
		String url = MARVEL_API_CHARACTER_COMICS_URL.replace("{characterId}", idCharacter.toString());
		ComicResponse comicResponse = new RestTemplate().getForObject(url + this.generateURLParams(login) + "&format=comic", ComicResponse.class);
		return comicResponse.getData().getResults();
	}

	private String generateURLParams(Login login) throws Exception {
		Long timestamp = new Date().getTime();
		byte[] bytes = MessageDigest.getInstance("MD5").digest((timestamp + login.getPrivateKey() + login.getPublicKey()).getBytes("UTF-8"));
		String hash = MD5Encoder.encode(bytes);
		return "?apikey=" + login.getPublicKey() + "&ts=" + timestamp + "&hash=" + hash;
	}

}
