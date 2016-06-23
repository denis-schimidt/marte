package com.elo7.marte.domain.model.sonda;

public enum Comando {
	LEFT("L", p -> PosicaoDirecional.builder().naCoordenada(p.getCoordenada()).apontandoPara(p.getDirecao().getEsquerda()).build()), 
	RIGHT("R",p -> PosicaoDirecional.builder().naCoordenada(p.getCoordenada()).apontandoPara(p.getDirecao().getDireita()).build()),
	MOVES_FOWARD("M", p-> PosicaoDirecional.builder().naCoordenada(p.getCoordenada().moverAFrente(p.getDirecao())).apontandoPara(p.getDirecao()).build());

	private String codigo;
	private Acao acao;

	public Acao getAcao() {
		return acao;
	}

	public String getCodigo() {
		return codigo;
	}
	
	private Comando(String codigo, Acao acao) {
		this.codigo = codigo;
		this.acao = acao;
	}
	
	public static Comando getInstance(String comandoAsCode){
		
		for(Comando comando : Comando.values() ){
			if(comando.codigo.equals(comandoAsCode)){
				return comando;
			}
		}
		
		throw new IllegalArgumentException(String.format("Comando(%s) não mapeado.", comandoAsCode));
	}
}