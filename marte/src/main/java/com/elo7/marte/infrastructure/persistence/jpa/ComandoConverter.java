package com.elo7.marte.infrastructure.persistence.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.lang3.StringUtils;

import com.elo7.marte.domain.model.sonda.Comando;

@Converter(autoApply=true)
public class ComandoConverter implements AttributeConverter<Comando, String> {
	
	@Override
	public String convertToDatabaseColumn(Comando comando) {
		
		if(comando!=null){
			return comando.getCodigo();
		}

		return "";
	}

	@Override
	public Comando convertToEntityAttribute(String comandoAsCode) {
		
		if(StringUtils.isNotBlank(comandoAsCode)){
			return Comando.getInstance(comandoAsCode);
		}

		return null;
	}
}