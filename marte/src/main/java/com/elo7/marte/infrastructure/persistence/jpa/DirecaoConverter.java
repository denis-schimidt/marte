package com.elo7.marte.infrastructure.persistence.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.lang3.StringUtils;

import com.elo7.marte.domain.sonda.Direcao;

@Converter(autoApply=true)
public class DirecaoConverter implements AttributeConverter<Direcao, String> {
	
	@Override
	public String convertToDatabaseColumn(Direcao direcao) {
		
		if(direcao!=null){
			return direcao.getCodigo();
		}

		return "";
	}

	@Override
	public Direcao convertToEntityAttribute(String comandoAsCode) {
		
		if(StringUtils.isNotBlank(comandoAsCode)){
			return Direcao.getInstance(comandoAsCode);
		}

		return null;
	}
}