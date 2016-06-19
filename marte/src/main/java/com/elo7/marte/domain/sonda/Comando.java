package com.elo7.marte.domain.sonda;

public enum Comando {
	LEFT("L", p -> new PosicaoDirecional(p.getCoordenada(), p.getDirecao().getEsquerda())), 
	RIGHT("R",p -> new PosicaoDirecional(p.getCoordenada(), p.getDirecao().getDireita())), 
	MOVES_FOWARD("M", p-> {
		MudancaCoordenada mudancaCoordenada = MudancaCoordenada.irParaFrente(p.getDirecao());
		Coordenada novaCoordenada = p.getCoordenada().mudarCoordenada(mudancaCoordenada);
		return new PosicaoDirecional(novaCoordenada, p.getDirecao());
	});

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