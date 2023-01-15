package com.github.fabriciossouza.rickandmortyapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	NOT_FOUND("/recurso-nao-encontrado", "Recurso não encontrado"),
	INCOMPREENSIBLE_MESSAGE("/mensagem-incompreensivel", "Mensagem incompreensível"),
	INVALID_DATA("/dados-invalidos", "Dados inválidos");

	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = path;
		this.title = title;
	}
	
}
