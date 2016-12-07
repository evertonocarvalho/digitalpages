package br.com.digitalpages.marvel.dao;

import br.com.digitalpages.marvel.model.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
@Repository
public interface CharacterRepository extends CrudRepository<Character, Long> {
}