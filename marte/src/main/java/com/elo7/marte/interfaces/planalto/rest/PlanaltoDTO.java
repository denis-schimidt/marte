package com.elo7.marte.interfaces.planalto.rest;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.elo7.marte.domain.model.planalto.Planalto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="planalto")
public class PlanaltoDTO {
	private Integer id;
	
	@Min(value=0, message="{planaltoDTO.limiteMaximoX.limiteMinimo}")
	@JsonProperty(value="limite_maximo_x", required=true)
	private int limiteMaximoX;
	
	@Min(value=0, message="{planaltoDTO.limiteMaximoY.limiteMinimo}")
	@JsonProperty(value="limite_maximo_y", required=true)
	private int limiteMaximoY;
	
	@NotBlank(message="{planaltoDTO.nome.emBranco}")
	@Size(min=5, max=50, message="{planaltoDTO.nome.tamanhoInvalido}")
	private String nome;

	public PlanaltoDTO() {}
	
	public PlanaltoDTO(Planalto planalto){
		this.id = planalto.getId();
		this.limiteMaximoX = planalto.getPontoMaximoX();
		this.limiteMaximoY = planalto.getPontoMaximoY();
		this.nome=planalto.getNome();
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
	
	public String getNome() {
		return nome;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
