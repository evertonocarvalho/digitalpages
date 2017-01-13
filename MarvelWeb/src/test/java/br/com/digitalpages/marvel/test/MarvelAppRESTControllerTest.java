package br.com.digitalpages.marvel.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.apache.log4j.Logger;
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

/**
 * @author Everton Carvalho [evertonocarvalho@gmail.com]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarvelAppBoot.class)
@WebAppConfiguration
public class MarvelAppRESTControllerTest {

	private static Logger LOGGER = Logger.getLogger(MarvelAppRESTControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void listCharacters() throws Exception {
    	LOGGER.info("listCharacters(): LISTANDO OBJETOS...");
    	mockMvc.perform(get("http://localhost:8080/characters.json/list")).andExpect(status().isOk());
    	LOGGER.info("listCharacters(): LISTANDO OBJETOS => OK...");
    }

    @Test
    public void insertCharacter() throws Exception {
    	LOGGER.info("insertCharacter(): INSERINDO OBJETO...");
        mockMvc.perform(post("http://localhost:8080/characters.json/insert")
                .content("{\"id\":1, \"name\":\"Test Character 1\", \"description\":\"Test Character Description 1\", \"thumbnailPath\":\"http://test.thumbnail.path.1\", \"thumbnailExtension\": \"jpg\", \"dataModificacao\":\"08/12/2016 12:30\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        LOGGER.info("insertCharacter(): INSERINDO OBJETO => OK...");
    }

    @Test
    public void findCharacter() throws Exception {
    	LOGGER.info("findCharacter(): CONSULTANDO OBJETO...");
        mockMvc.perform(get("http://localhost:8080/characters.json/get/{id}", 1L)).andExpect(status().isOk());
        LOGGER.info("findCharacter(): CONSULTANDO OBJETO => OK...");
    }

    @Test
    public void updateCharacter() throws Exception {
    	LOGGER.info("updateCharacter(): ATUALIZANDO OBJETO...");
        mockMvc.perform(put("http://localhost:8080/characters.json/update/{id}", 1L)
        		.content("{\"id\":1, \"name\":\"Test Character 1 Updated\", \"description\":\"Test Character Description 1 Updated\", \"thumbnailPath\":\"http://test.thumbnail.path.1\", \"thumbnailExtension\": \"jpg\", \"dataModificacao\":\"08/12/2016 12:31\"}")
        		.contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(status().isOk());
        LOGGER.info("updateCharacter(): ATUALIZANDO OBJETO => OK...");
    }

    @Test
    public void deleteCharacter() throws Exception {
    	LOGGER.info("deleteCharacter(): REMOVENDO OBJETO...");
        mockMvc.perform(delete("http://localhost:8080/characters.json/delete/{id}", 1L)).andExpect(status().isOk());
        LOGGER.info("deleteCharacter(): REMOVENDO OBJETO => OK...");
    }

}
