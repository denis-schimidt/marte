package com.elo7.marte.infrastructure.aspects;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class PageRequestFactory {
	
	public PageRequest from(PageRequest pageRequestInicial, Map<String, String> mapaNomeCampoNaClassePorJsonProperty){
		
		List<Order> listaCamposAOrdenar = listarOrders(pageRequestInicial)
			.stream()
			.map(order-> obterOrderAdequadoCampo(pageRequestInicial, order, mapaNomeCampoNaClassePorJsonProperty.get(order.getProperty())))
			.collect(Collectors.toList());
			
		return criarNovoPageRequest(pageRequestInicial, criarNovoSort(listaCamposAOrdenar));
	}

	private Order obterOrderAdequadoCampo(PageRequest pageRequestInicial, Order order, String nomeCampoEncontradoPeloJson) {
		
		if(nomeCampoEncontradoPeloJson!= null){
			return gerarOrderComNomeCampoClasse(pageRequestInicial.getSort(), order.getProperty(), nomeCampoEncontradoPeloJson);
		
		}else{
			return order;
		}
	}

	private Sort criarNovoSort(List<Order> listaCamposAOrdenar) {
		return new Sort(listaCamposAOrdenar.toArray(new Order[]{}));
	}

	private PageRequest criarNovoPageRequest(PageRequest pageRequestInicial, Sort sortBaseadoCamposClasse) {
		return new PageRequest(pageRequestInicial.getPageNumber(), pageRequestInicial.getPageSize(), sortBaseadoCamposClasse);
	}
	
	private List<Order> listarOrders(PageRequest pageRequest){
		return Lists.newArrayList(pageRequest.getSort().iterator());
	}
	
	private Order gerarOrderComNomeCampoClasse(Sort sort, String nomeCampoJson, String nomeCampoClasse){
		Order orderFor = sort.getOrderFor(nomeCampoJson);
		
		return orderFor.withProperties(nomeCampoClasse).iterator().next();
	}
}
