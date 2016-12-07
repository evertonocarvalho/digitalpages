package br.com.digitalpages.marvel.model;

import java.util.List;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
public class CharacterDataResponse {

	private List<Character> results;

	public List<Character> getResults() {
		return this.results;
	}

	public void setResults(List<Character> results) {
		this.results = results;
	}

}
