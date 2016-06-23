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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.elo7.marte.domain.model.sonda.Coordenada;
import com.elo7.marte.domain.model.sonda.CoordenadaForaDoPlanaltoException;
import com.google.common.base.Preconditions;

@Table(name="planalto")
@Entity
public class Planalto{

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false, name = "ponto_maximo_x")
	private int pontoMaximoX;

	@Column(nullable = false, name = "ponto_maximo_y")
	private int pontoMaximoY;
	
	@OneToMany(cascade=CascadeType.PERSIST,mappedBy="planalto")
	private Set<SondaNoPlanalto> sondasNoPlanalto;

	Planalto() {}

	public Planalto(int pontoMaximoX, int pontoMaximoY) {
		Preconditions.checkArgument(pontoMaximoX >= 0, "O ponto máximo X do planalto não pode ser menor que zero.");
		Preconditions.checkArgument(pontoMaximoY >= 0, "O ponto máximo Y do planalto não pode ser menor que zero.");

		this.pontoMaximoX = pontoMaximoX;
		this.pontoMaximoY = pontoMaximoY;
		
		this.sondasNoPlanalto = new HashSet<>();
	}

	public void validarCoordenadaDentroPlanalto(Coordenada coordenada) throws CoordenadaForaDoPlanaltoException {
		Preconditions.checkArgument(coordenada != null, "A coordenada não pode ser nula para o planalto.");

		boolean isCoordenadaValida = coordenada.getEixoX() >= 0 && coordenada.getEixoX() <= getPontoMaximoX()
				&& coordenada.getEixoY() >= 0 && coordenada.getEixoY() <= getPontoMaximoY();

		if (!isCoordenadaValida) {
			throw new CoordenadaForaDoPlanaltoException("A %s está fora dos limites do %s", coordenada, this);
		}
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
