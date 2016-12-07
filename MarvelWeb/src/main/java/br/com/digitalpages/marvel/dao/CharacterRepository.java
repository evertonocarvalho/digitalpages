package br.com.digitalpages.marvel.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.digitalpages.marvel.model.Character;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
@Repository
public interface CharacterRepository extends PagingAndSortingRepository<Character, Long> {
}