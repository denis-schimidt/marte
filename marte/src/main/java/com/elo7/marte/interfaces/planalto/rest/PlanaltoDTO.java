package com.elo7.marte.interfaces.planalto.rest;

import javax.validation.constraints.Min;

import com.elo7.marte.domain.model.planalto.Planalto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="planalto")
public class PlanaltoDTO {
	private Integer id;
	
	@Min(value=0, message="limite x inválido")
	@JsonProperty(value="limite_maximo_x", required=true)
	private int limiteMaximoX;
	
	@Min(value=0, message="limite y inválido")
	@JsonProperty(value="limite_maximo_y", required=true)
	private int limiteMaximoY;

	public PlanaltoDTO() {}
	
	public PlanaltoDTO(Planalto planalto){
		this.id = planalto.getId();
		this.limiteMaximoX = planalto.getPontoMaximoX();
		this.limiteMaximoY = planalto.getPontoMaximoY();
	}
	
	public int getLimiteMaximoX() {
		return limiteMaximoX;
	}

	public int getLimiteMaximoY() {
		return limiteMaximoY;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
