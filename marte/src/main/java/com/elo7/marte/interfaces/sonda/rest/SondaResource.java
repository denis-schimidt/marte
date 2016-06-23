package com.elo7.marte.interfaces.sonda.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elo7.marte.domain.model.sonda.PosicaoDirecional;

@RestController
public class SondaResource {

	@RequestMapping("/")
	@ResponseBody
	public ResponseEntity<PosicaoDirecional> executarComandos(SondaDTO sonda) {
		return null;
	}
}
