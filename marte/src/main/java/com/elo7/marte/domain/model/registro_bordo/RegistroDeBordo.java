package com.elo7.marte.domain.model.registro_bordo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.elo7.marte.domain.model.planalto.SondaNoPlanalto;
import com.elo7.marte.domain.model.sonda.Comando;
import com.elo7.marte.domain.model.sonda.PosicaoDirecional;
import com.google.common.base.MoreObjects;

@Table(name = "registro_de_bordo", indexes = {
		@Index(columnList = "planalto, direcao, eixo_x, eixo_y, status_movimentacao") })
@Entity
public class RegistroDeBordo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumns(value = { @JoinColumn(name = "sonda", referencedColumnName = "id_sonda", nullable = false),
			@JoinColumn(name = "planalto", referencedColumnName = "id_planalto", nullable = false) })
	private SondaNoPlanalto sondaNoPlanalto;

	private PosicaoDirecional posicaoPosComando;

	@Column(name="comando_executado", length=1)
	private Comando comandoExecutado;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_movimentacao", nullable = false)
	private StatusMovimentacaoSonda statusMovimentacaoSonda;

	@Column(nullable = false, updatable = false, name = "data_hora_gravacao")
	private LocalDateTime dataHoraGravacao;

	RegistroDeBordo() {}

	public RegistroDeBordo(SondaNoPlanalto sondaNoPlanalto, Comando comandoExecutado,
			PosicaoDirecional posicaoPosComando, StatusMovimentacaoSonda statusMovimentacaoSonda) {
		this.sondaNoPlanalto = sondaNoPlanalto;
		this.comandoExecutado = comandoExecutado;
		this.posicaoPosComando = posicaoPosComando;
		this.dataHoraGravacao = LocalDateTime.now();
		this.statusMovimentacaoSonda = statusMovimentacaoSonda;
	}

	public Long getId() {
		return id;
	}

	public SondaNoPlanalto getSondaNoPlanalto() {
		return sondaNoPlanalto;
	}

	public Comando getComandoExecutado() {
		return comandoExecutado;
	}

	public PosicaoDirecional getPosicaoPosComando() {
		return posicaoPosComando;
	}

	public LocalDateTime getDataHoraGravacao() {
		return dataHoraGravacao;
	}

	public StatusMovimentacaoSonda getStatusDaMovimentacao() {
		return statusMovimentacaoSonda;
	}
	
	public void encerrarRegistro(){
		statusMovimentacaoSonda = StatusMovimentacaoSonda.POSICAO_FINAL;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sondaNoPlanalto, posicaoPosComando, comandoExecutado, dataHoraGravacao, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroDeBordo other = (RegistroDeBordo) obj;

		return Objects.equals(sondaNoPlanalto, other.sondaNoPlanalto)
				&& Objects.equals(posicaoPosComando, other.posicaoPosComando)
				&& Objects.equals(comandoExecutado, other.comandoExecutado)
				&& Objects.equals(dataHoraGravacao, other.dataHoraGravacao) 
				&& Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(RegistroDeBordo.class)
				.add("sondaId", sondaNoPlanalto.getSonda().getId())
				.add("planaltoId", sondaNoPlanalto.getPlanalto().getId())
				.add("posicaoPosComando", posicaoPosComando)
				.add("comandoExecutado", comandoExecutado)
				.add("dataHoraGravacao", dataHoraGravacao)
				.add("id", id)
				.add("statusMovimentacaoSonda", statusMovimentacaoSonda)
				.toString();
	}
}
