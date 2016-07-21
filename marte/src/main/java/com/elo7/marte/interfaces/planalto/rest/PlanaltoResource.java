package com.elo7.marte.interfaces.planalto.rest;

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

import com.elo7.marte.application.aspects.JsonSortFieldsConverter;
import com.elo7.marte.domain.model.planalto.Planalto;
import com.elo7.marte.domain.model.planalto.PlanaltoRepository;
import com.elo7.marte.interfaces.json.View;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/planaltos", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PlanaltoResource {
	
	@Autowired
	private PlanaltoRepository repository;

	@RequestMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@JsonView(View.DTO.class)
	@Transactional
	public Planalto salvar(@Valid @NotNull @RequestBody Planalto planalto) {
		return repository.save(planalto);
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.GET)
	@JsonView(View.DTO.class)
	public ResponseEntity<Planalto> pesquisarPorId(@PathVariable("id") Integer id) {
		
		Planalto planalto = repository.findOne(id);
		
		if(planalto!=null){
			return ResponseEntity.ok(planalto);
		}

		return new ResponseEntity<Planalto>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@JsonView(View.DTO.class)
	@JsonSortFieldsConverter(Planalto.class)
	public ResponseEntity<List<Planalto>> listarTodos(@PageableDefault(page=0, size=20, sort="nome") Pageable pagina){
		Page<Planalto> page = repository.findAll(pagina);
		
		if(page.getNumberOfElements() > 0){
			return ResponseEntity.ok(page.getContent());
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
	}
}
