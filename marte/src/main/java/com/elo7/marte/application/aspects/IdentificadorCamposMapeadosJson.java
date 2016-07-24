package com.elo7.marte.application.aspects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

public class IdentificadorCamposMapeadosJson {

	public static boolean isCamposPrecisamSerConvertidosOrdenacao(JsonSortFieldsConverter jsonSortFieldsConverter){
		return Lists.newArrayList(jsonSortFieldsConverter.value().getDeclaredFields())
				.stream()
				.filter(f-> f.getAnnotationsByType(JsonProperty.class).length > 0)
				.findAny()
				.isPresent();
	}
}
