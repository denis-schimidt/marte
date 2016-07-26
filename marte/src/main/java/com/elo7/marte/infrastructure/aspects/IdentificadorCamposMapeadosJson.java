package com.elo7.marte.infrastructure.aspects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

public class IdentificadorCamposMapeadosJson {

	public static boolean isCamposPrecisamSerConvertidosOrdenacao(JsonSortFieldsConverter jsonSortFieldsConverter){
		return Lists.newArrayList(jsonSortFieldsConverter.value().getDeclaredMethods())
				.stream()
				.filter(m-> m.getAnnotationsByType(JsonProperty.class).length > 0)
				.findAny()
				.isPresent();
	}
}
