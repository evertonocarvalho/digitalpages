package br.com.digitalpages.marvel.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.digitalpages.marvel.MarvelAppBoot;
import br.com.digitalpages.marvel.dao.CharacterRepository;
import br.com.digitalpages.marvel.model.Character;

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarvelAppBoot.class)
@WebAppConfiguration
public class MarvelAppRESTControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CharacterRepository characterRepository;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        List<Character> characters = new ArrayList<>();

    	for (int i = 1; i <= 10; i++) {
    		Character character = new Character();
    		character.setId(Long.valueOf(i));
    		character.setName("Test Character " + i);
    		character.setDescription("Test Character Description " + i);
    		character.setDataModificacao(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
    		character.setThumbnailPath("http://test.thumbnail.path." + i);
    		character.setThumbnailExtension("jpg");
    		characters.add(character);
    	}

    	characterRepository.save(characters);
    }

    @Test
    public void listCharacters() throws Exception {
    	mockMvc.perform(get("http://localhost:8080/characters.json/list")).andExpect(status().isOk());
    }

    @Test
    public void insertCharacter() throws Exception {
        mockMvc.perform(post("http://localhost:8080/characters.json/insert")
                .content("{\"id\":11, \"name\":\"Test Character 11\", \"description\":\"Test Character Description 11\", \"thumbnailPath\":\"http://test.thumbnail.path.11\", \"thumbnailExtension\": \"jpg\", \"dataModificacao\":\"08/12/2016 12:30\"}")
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void findCharacter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/characters.json/get/{id}", 11L)).andExpect(status().isOk());
    }

    @Test
    public void updateCharacter() throws Exception {
        mockMvc.perform(put("http://localhost:8080/characters.json/update/{id}", 11L)
        		.content("{\"id\":11, \"name\":\"Test Character 11 Updated\", \"description\":\"Test Character Description 11 Updated\", \"thumbnailPath\":\"http://test.thumbnail.path.11\", \"thumbnailExtension\": \"jpg\", \"dataModificacao\":\"08/12/2016 12:31\"}")
        		.contentType(contentType))
        		.andExpect(status().isOk());
    }

    @Test
    public void deleteCharacter() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/characters.json/delete/{id}", 1L)).andExpect(status().isOk());
    }

    @After
    public void deleteCharacters() throws Exception {
    	characterRepository.deleteAll();
    }

}
