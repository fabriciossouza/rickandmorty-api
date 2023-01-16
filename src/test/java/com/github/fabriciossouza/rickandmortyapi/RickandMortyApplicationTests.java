package com.github.fabriciossouza.rickandmortyapi;


import com.github.fabriciossouza.rickandmortyapi.api.controller.character.dto.CharacterResponse;
import com.github.fabriciossouza.rickandmortyapi.api.controller.character.dto.CharactersResponse;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RickandMortyApplicationTests {

	@LocalServerPort
	private int port;

	private String BASE_URL;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	void set() {
		BASE_URL = "http://localhost:" + port + "/api/character";
	}

	@Test
	@DisplayName("Deve retornar listagem paginada de todos os personagens")
	public void deveRetornarUmaPaginaPaginadaDosPersonagens() throws Exception {
		final String url = new URL(BASE_URL).toString();

		var response = restTemplate.getForEntity(url, CharactersResponse.class);
		List<CharacterResponse> characters = response.getBody().getCharacters();

		assertEquals(response.getStatusCode(), OK);
		assertThat(characters).hasSize(20);
	}

	@Test
	@SneakyThrows
	@DisplayName("Deve validar o retorno da lista de todos os personagens")
	public void devaValidarRetornoDaListagemDePersonagens() throws Exception {
		final String url = new URL(BASE_URL).toString();

		var response = restTemplate.getForEntity(url, String.class);
		assertEquals(response.getStatusCode(), OK);

		var jsonResponse = new JSONObject(response.getBody());

		var pageResponse = jsonResponse.getJSONObject("info");
		var characters = jsonResponse.getJSONArray("characters");

		assertTrue(pageResponse.has("count"));
		assertTrue(pageResponse.has("pages"));
		assertTrue(pageResponse.has("next"));
		assertTrue(pageResponse.has("prev"));
		assertThat(characters.length()).isEqualTo(20);
	}

	@Test
	@DisplayName("Deve Retornar 404 quando o personagem não existir")
	public void deveRetornar404QuandoInformarUmNomeInvalido() throws Exception {
		final String url = new URL(BASE_URL + "?name=QWERTYYIY").toString();

		ResponseEntity<CharactersResponse> response = restTemplate.getForEntity(url, CharactersResponse.class);
		assertEquals(response.getStatusCode(), NOT_FOUND);
	}

	@Test
	@DisplayName("Deve Retornar 400 quando o filtro nome informado for muito grande")
	public void deveRetornar400QuandoNomeInformaForGrandeDemais() throws Exception {
		final String name = randomUUID().toString();
		final String url = new URL(BASE_URL + "?name=" + name).toString();

		ResponseEntity<CharactersResponse> response = restTemplate.getForEntity(url, CharactersResponse.class);
		assertEquals(response.getStatusCode(), NOT_FOUND);
	}

}