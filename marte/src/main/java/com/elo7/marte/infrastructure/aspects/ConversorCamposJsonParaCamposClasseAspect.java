package com.elo7.marte.infrastructure.aspects;

import static java.util.stream.Collectors.toMap;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

@Component
@Aspect
public class ConversorCamposJsonParaCamposClasseAspect {
	
	@Autowired
	private PageRequestFactory pageRequestFactory;
	
	@Around("execution(* *(..)) && @annotation(jsonSortFieldsConverter)")
	public Object around(ProceedingJoinPoint point, JsonSortFieldsConverter jsonSortFieldsConverter) throws Throwable {
		PageRequest pageRequest = encontrarPageRequest(point);
		
		if(IdentificadorCamposMapeadosJson.isCamposPrecisamSerConvertidosOrdenacao(jsonSortFieldsConverter)){
			final Map<String, String> mapaNomeCampoClassePorJsonProperty = criarMapaComNameFieldPorJsonField(jsonSortFieldsConverter);
			PageRequest pageRequestConsiderandoNomeCamposClasse = pageRequestFactory.from(pageRequest, mapaNomeCampoClassePorJsonProperty);
			
			return point.proceed(new Object[]{pageRequestConsiderandoNomeCamposClasse});	
		}
		
		return point.proceed();
	}

	private Map<String, String> criarMapaComNameFieldPorJsonField(JsonSortFieldsConverter jsonSortFieldsConverter) {
		final Map<String, String> mapaComNameFieldPorJsonField = Lists.newArrayList(jsonSortFieldsConverter.value().getDeclaredMethods())
			.stream()
			.filter(m-> m.getAnnotationsByType(JsonProperty.class).length > 0)
			.map(m-> new RelacionamentoNomeCampoJsonNomeCampoClasse(m.getAnnotationsByType(JsonProperty.class)[0], m.getName())) 
			.collect(toMap(RelacionamentoNomeCampoJsonNomeCampoClasse::getNomeCampoJson, RelacionamentoNomeCampoJsonNomeCampoClasse::getNomeCampoClasse));
		
		return mapaComNameFieldPorJsonField;
	}
	
	private PageRequest encontrarPageRequest(ProceedingJoinPoint point){
		
		for(Object param :point.getArgs()){
			if(param instanceof PageRequest){
				return (PageRequest) param;
			}
		}
		
		return new PageRequest(0, 20);
	}
	
	private class RelacionamentoNomeCampoJsonNomeCampoClasse{
		private JsonProperty jsonProperty;
		private String nomeCampoClasse;

		public RelacionamentoNomeCampoJsonNomeCampoClasse(JsonProperty jsonProperty, String nomeMetodoClasse) {
			this.jsonProperty = jsonProperty;
			
			StringBuilder nomeCampoBuilder = new StringBuilder(nomeMetodoClasse.substring(3));
			nomeCampoBuilder.setCharAt(0, Character.toUpperCase(nomeCampoBuilder.charAt(0)));
			
			this.nomeCampoClasse = nomeCampoBuilder.toString();
		}

		public String getNomeCampoJson() {
			return jsonProperty.value();
		}
		
		public String getNomeCampoClasse() {
			return nomeCampoClasse;
		}
	}
}
