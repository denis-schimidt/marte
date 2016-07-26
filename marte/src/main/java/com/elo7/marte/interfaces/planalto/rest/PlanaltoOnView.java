package com.elo7.marte.interfaces.planalto.rest;

import com.elo7.marte.interfaces.json.view.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

public interface PlanaltoOnView {

	@JsonView(DTO.class)
	Integer getId();

	@JsonView(DTO.class)
	@JsonProperty(defaultValue="0", value="ponto_maximo_x")
	int getPontoMaximoX();

	@JsonView(DTO.class)
	@JsonProperty(defaultValue="0", value="ponto_maximo_y")
	int getPontoMaximoY();

	@JsonView(DTO.class)
	String getNome();
}