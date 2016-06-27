package com.elo7.marte.domain.model.sonda;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.elo7.marte.domain.model.planalto.Planalto;
import com.elo7.marte.domain.model.planalto.PlanaltoRequeridoException;
import com.elo7.marte.domain.model.planalto.PossivelColisaoSondasNoPlanaltoException;
import com.elo7.marte.domain.model.planalto.SondaNoPlanalto;
import com.elo7.marte.domain.model.registro_bordo.StatusMovimentacaoSonda;
import com.google.common.base.Preconditions;

@Table(name="sonda")
@Entity
public class Sonda{

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="sonda", orphanRemoval=true)
	private Set<SondaNoPlanalto> sondasPlanalto;
	
	private PosicaoDirecional posicaoAtual;
	
	Sonda(){}
	
	public Sonda(final PosicaoDirecional posicaoInicial, final Planalto planalto) {
		Preconditions.checkArgument(posicaoInicial!=null, "A posição inicial não pode ser nula para a sonda.");
		Preconditions.checkArgument(planalto!=null, "O planalto não pode ser nulo para a sonda.");
		
		this.posicaoAtual = posicaoInicial;
		
		this.sondasPlanalto = new HashSet<>();
		
		SondaNoPlanalto sondaNoPlanalto = new SondaNoPlanalto(this, planalto);
		sondasPlanalto.add(sondaNoPlanalto);
	}

	public void iniciarExploracao(final List<Comando> comandos, final Set<Coordenada> posicoesOcupadasOutrasSondas) {
		
		SondaNoPlanalto sondaNoPlanalto = obterSondaNoPlanaltoAtual();
		int indice = 0;
		
		for (Comando comando : comandos) {
			final PosicaoDirecional possivelNovaPosicao = posicaoAtual.atualizarPosicao(comando);
			
			try{
				sondaNoPlanalto.validarPosicaoPlanalto(possivelNovaPosicao, posicoesOcupadasOutrasSondas);
				this.posicaoAtual = possivelNovaPosicao;
				indice++;
				
				StatusMovimentacaoSonda status = (indice == comandos.size()) ? StatusMovimentacaoSonda.POSICAO_FINAL : StatusMovimentacaoSonda.EM_MOVIMENTO;
				sondaNoPlanalto.gravarRegistroBordo(comando,posicaoAtual, status);
				
			}catch (CoordenadaForaDoPlanaltoException e) {
				sondaNoPlanalto.gravarRegistroBordo(comando,posicaoAtual,StatusMovimentacaoSonda.PARADO_PELO_LIMITE_PLANALTO);
				return;
				
			}catch (PossivelColisaoSondasNoPlanaltoException e) {
				sondaNoPlanalto.gravarRegistroBordo(comando,posicaoAtual,StatusMovimentacaoSonda.PARADO_PARA_EVITAR_COLISAO);
				return;
			}
		}
	}
	
	private SondaNoPlanalto obterSondaNoPlanaltoAtual(){
		
		return sondasPlanalto
				.stream()
				.filter(SondaNoPlanalto::isSondaAtivaNoPlanalto)
				.findFirst()
				.orElseThrow(()-> new PlanaltoRequeridoException("Não existe nenhum planalto sendo explorado pela sonda."));
	}

	public Long getId() {
		return id;
	}
	
	public PosicaoDirecional getPosicaoAtual() {
		return posicaoAtual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(posicaoAtual, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sonda other = (Sonda) obj;
		
		return Objects.equals(posicaoAtual, other.posicaoAtual) &&
				Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
