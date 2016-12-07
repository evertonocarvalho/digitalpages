package br.com.digitalpages.marvel.model;

import java.util.List;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
public class ComicDataResponse {

	private List<Comic> results;

	public List<Comic> getResults() {
		return this.results;
	}

	public void setResults(List<Comic> results) {
		this.results = results;
	}

}
