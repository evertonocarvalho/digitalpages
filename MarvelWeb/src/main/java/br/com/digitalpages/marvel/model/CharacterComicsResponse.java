package br.com.digitalpages.marvel.model;

import java.util.List;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
public class CharacterComicsResponse {

	private List<CharacterComicsItemResponse> items;

	public List<CharacterComicsItemResponse> getItems() {
		return this.items;
	}

	public void setItems(List<CharacterComicsItemResponse> items) {
		this.items = items;
	}

}
