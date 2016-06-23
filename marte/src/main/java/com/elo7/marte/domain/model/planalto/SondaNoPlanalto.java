package com.elo7.marte.domain.model.planalto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.elo7.marte.domain.model.registro_bordo.RegistroDeBordo;
import com.elo7.marte.domain.model.registro_bordo.StatusMovimentacaoSonda;
import com.elo7.marte.domain.model.sonda.Comando;
import com.elo7.marte.domain.model.sonda.CoordenadaForaDoPlanaltoException;
import com.elo7.marte.domain.model.sonda.PosicaoDirecional;
import com.elo7.marte.domain.model.sonda.Sonda;
import com.google.common.base.MoreObjects;

@IdClass(IdPosicaoPlanalto.class)
@Table(name="sonda_planalto",uniqueConstraints=@UniqueConstraint(columnNames={"id_sonda", "id_planalto", "data_hora_inicio"}))
@Entity
public class SondaNoPlanalto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_sonda", updatable=false, nullable=false)
	private Sonda sonda;
	
	@Id
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_planalto", updatable=false, nullable=false)
	private Planalto planalto;
	
	@Column(name="data_hora_inicio", updatable=false, nullable=false)
	private LocalDateTime dataHoraInicio;
	
	@Column(nullable=false)
	private boolean ativa;
	
	@OneToMany(orphanRemoval=true, cascade=CascadeType.PERSIST, mappedBy="sondaNoPlanalto")
	private Set<RegistroDeBordo> registrosDeBordo;
	
	SondaNoPlanalto() {}

	public SondaNoPlanalto(Sonda sonda, Planalto planalto) {
		this.sonda = sonda;
		this.planalto = planalto;
		this.dataHoraInicio = LocalDateTime.now();
		this.ativa = true;
		
		this.registrosDeBordo = new LinkedHashSet<>();
		registrosDeBordo.add(new RegistroDeBordo(this, null, sonda.getPosicaoAtual(), StatusMovimentacaoSonda.POSICAO_INICIAL));
	}
	
	public boolean isSondaAtivaNoPlanalto(){
		return ativa;
	}
	
	public void finalizarExploracaoPlanalto(){
		this.ativa = false;
	}
	
	public void gravarRegistroBordo(Comando comando, PosicaoDirecional posicaoAtual, StatusMovimentacaoSonda statusDaMovimentacao) {
		registrosDeBordo.add(new RegistroDeBordo(this, comando, posicaoAtual, statusDaMovimentacao));
	}
	
	public void validarPosicaoPlanalto(PosicaoDirecional posicaoDirecional, Set<PosicaoDirecional> posicoesOcupadasOutrasSondas) throws CoordenadaForaDoPlanaltoException, 
			PossivelColisaoSondasNoPlanaltoException {
		
		planalto.validarCoordenadaDentroPlanalto(posicaoDirecional.getCoordenada());
		
		if(posicoesOcupadasOutrasSondas!=null && posicoesOcupadasOutrasSondas.contains(posicaoDirecional)){
			throw new PossivelColisaoSondasNoPlanaltoException("Interrupção de comandos devido a possível colisão de sondas na %s.", posicaoDirecional);
		}
	}
	
	public Set<RegistroDeBordo> getRegistrosDeBordo() {
		return registrosDeBordo;
	}

	public Sonda getSonda() {
		return sonda;
	}

	public Planalto getPlanalto() {
		return planalto;
	}

	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(planalto, sonda, dataHoraInicio, ativa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SondaNoPlanalto other = (SondaNoPlanalto) obj;
		
		return Objects.equals(planalto, other.planalto) &&
				Objects.equals(sonda, other.sonda) &&
				Objects.equals(dataHoraInicio, other.dataHoraInicio) &&
				Objects.equals(ativa, other.ativa);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(SondaNoPlanalto.class)
				.add("sondaId", sonda.getId())
				.add("planaltoId", planalto.getId())
				.add("dataHoraInicio", dataHoraInicio)
				.add("ativa", ativa)
				.add("registrosDeBordo", registrosDeBordo)
				.toString();
	}
}
