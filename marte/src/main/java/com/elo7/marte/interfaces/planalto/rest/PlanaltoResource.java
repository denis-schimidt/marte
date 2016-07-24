package com.elo7.marte.interfaces.planalto.rest;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elo7.marte.application.aspects.JsonSortFieldsConverter;
import com.elo7.marte.domain.model.planalto.Planalto;
import com.elo7.marte.domain.model.planalto.PlanaltoRepository;
import com.elo7.marte.interfaces.json.view.DTO;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;

@RestController
@RequestMapping(value="/sonda-marte/api/v1/planaltos")
@ApiModel(value="planaltos")
public class PlanaltoResource {
	
	@Autowired
	private PlanaltoRepository repository;
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<Void> incluir(@Valid @NotNull @RequestBody Planalto planalto) {
		Planalto planaltoIncluido = repository.save(planalto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(planaltoIncluido.getId().toString()).build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Void> alterar(@Valid @NotNull @RequestBody Planalto planalto) {
		Planalto planaltoAtual = repository.findOne(planalto.getId());

		if(planaltoAtual!= null){
			planalto.adicionarSondas(planaltoAtual.getSondasPlanalto());
			
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.DELETE)
	@Transactional
	public ResponseEntity<Void> deletar(@NotNull(message="{planalto.id.NotNull}") Integer id) {
		Planalto planaltoAtual = repository.findOne(id);
		
		if(planaltoAtual!= null){
			repository.delete(id);
			
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.GET)
	@JsonView(DTO.class)
	public ResponseEntity<Planalto> pesquisarPorId(@PathVariable("id") Integer id) {
		
		Planalto planalto = repository.findOne(id);
		
		if(planalto!=null){
			return ResponseEntity.ok(planalto);
		}

		return new ResponseEntity<Planalto>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@JsonView(DTO.class)
	@JsonSortFieldsConverter(Planalto.class)
	public ResponseEntity<List<Planalto>> listarTodos(@PageableDefault(page=0, size=20, sort="nome") Pageable pagina){
		Page<Planalto> page = repository.findAll(pagina);
		
		if(page.getNumberOfElements() > 0){
			return ResponseEntity.ok(page.getContent());
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
	}
}
