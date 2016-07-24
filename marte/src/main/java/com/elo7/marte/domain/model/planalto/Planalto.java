package com.elo7.marte.domain.model.planalto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.elo7.marte.domain.model.sonda.Coordenada;
import com.elo7.marte.domain.model.sonda.CoordenadaForaDoPlanaltoException;
import com.elo7.marte.interfaces.json.view.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;

@Table(name="planalto")
@Entity
public class Planalto{

	@Id
	@GeneratedValue
	@JsonView(DTO.class)
	private Integer id;

	@JsonView(DTO.class)  @JsonProperty(defaultValue="0", value="ponto_maximo_x")
	@Min(value=0, message="{planalto.pontoMaximoX.Min}") 
	@Column(nullable = false, name = "ponto_maximo_x")
	private int pontoMaximoX;
	
	@JsonView(DTO.class)  @JsonProperty(defaultValue="0", value="ponto_maximo_y")
	@Min(value=0, message="{planalto.pontoMaximoY.Min}")
	@Column(nullable = false, name = "ponto_maximo_y")
	private int pontoMaximoY;
	
	@JsonView(DTO.class) 
	@Size(min=5, max=35, message="{planalto.nome.Size}") @NotBlank(message="{planalto.nome.NotBlank}")
	@Column(nullable=false, length=35)
	private String nome;
	
	@OneToMany(cascade=CascadeType.PERSIST,mappedBy="planalto")
	private Set<SondaNoPlanalto> sondasNoPlanalto;

	public void validarCoordenadaDentroPlanalto(Coordenada coordenada) throws CoordenadaForaDoPlanaltoException {
		Preconditions.checkArgument(coordenada != null, "A coordenada não pode ser nula para o planalto.");

		boolean isCoordenadaValida = coordenada.getEixoX() >= 0 && coordenada.getEixoX() <= getPontoMaximoX()
				&& coordenada.getEixoY() >= 0 && coordenada.getEixoY() <= getPontoMaximoY();

		if (!isCoordenadaValida) {
			throw new CoordenadaForaDoPlanaltoException("A %s está fora dos limites do %s", coordenada, this);
		}
	}
	
	public void adicionarSondas(Set<SondaNoPlanalto> novasSondaNoPlanaltos){
		if(this.sondasNoPlanalto == null){
			this.sondasNoPlanalto = new HashSet<>();
		}
		
		sondasNoPlanalto.addAll(novasSondaNoPlanaltos);
	}

	public Integer getId() {
		return id;
	}
	
	public int getPontoMaximoX() {
		return pontoMaximoX;
	}

	public int getPontoMaximoY() {
		return pontoMaximoY;
	}
	
	public String getNome() {
		return nome;
	}

	public Set<SondaNoPlanalto> getSondasPlanalto() {
		return Collections.unmodifiableSet(sondasNoPlanalto);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(pontoMaximoX, pontoMaximoY, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Planalto other = (Planalto) obj;

		return Objects.equals(pontoMaximoX, other.pontoMaximoX) && 
				Objects.equals(pontoMaximoY, other.pontoMaximoY) &&
				Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
