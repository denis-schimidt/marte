package com.elo7.marte.application.aspects;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

@Component
@Aspect
public class JsonSortFieldsAspect {

	@Around("execution(* *(..)) && @annotation(jsonSortFieldsConverter)")
	public Object around(ProceedingJoinPoint point, JsonSortFieldsConverter jsonSortFieldsConverter) throws Throwable {
		PageRequest pageRequest = null;
		
		final Map<String, String> mapaNomeCampoClassePorJsonProperty = Lists.newArrayList(jsonSortFieldsConverter.value().getDeclaredFields())
			.stream()
			.map(field-> field.getAnnotationsByType(JsonProperty.class)[0])
			.collect(Collectors.toMap(jsonField-> ((JsonProperty) jsonField).value(), field-> field.annotationType().getDeclaredFields()[0].getName()));
		
		//Transformar Map para List
		final List<Order> listaCamposAOrdenarComoJson = Lists.newArrayList(point.getArgs())
			.stream()
			.filter(arg-> arg instanceof PageRequest)
			.map(page->((PageRequest)page).getSort().iterator())
			.map(i->i.next())
			.collect(Collectors.toList());
		
		listaCamposAOrdenarComoJson.forEach(o-> {
			o.withProperties(mapaNomeCampoClassePorJsonProperty.get(o.getProperty()));
		});
		
		//camposMapeadosObjeto.
		
		/*for(Object param :point.getArgs()){
			if(param instanceof PageRequest){
				PageRequest pageRequestOriginal = (PageRequest) param;
				Order orderFor = pageRequestOriginal.getSort().getOrderFor(fieldInJson);
				
				if(orderFor != null && orderFor.getProperty().equals(fieldInJson)){
					Sort newSort = orderFor.withProperties(jsonSortFieldsConverter.value().getDeclaredFields()[2].getName());
					
					pageRequest = new PageRequest(pageRequestOriginal.getPageNumber(), pageRequestOriginal.getPageSize(), newSort);
				}
			}
		}*/
		
		
		return point.proceed(new Object[]{pageRequest});
	}
}
