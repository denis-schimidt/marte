package com.elo7.marte.infra;

import com.elo7.marte.model.RegistroDeBordo;
import com.elo7.marte.model.Sonda;

public interface SondaRepository {

	void salvar(Sonda sonda);

	void salvar(RegistroDeBordo historicoPosicao);

}
