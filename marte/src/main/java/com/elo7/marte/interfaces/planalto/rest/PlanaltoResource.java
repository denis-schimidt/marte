package com.elo7.marte.interfaces.planalto.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elo7.marte.domain.model.planalto.Planalto;
import com.elo7.marte.domain.model.planalto.PlanaltoRepository;

@RestController
@RequestMapping(value="/planaltos", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PlanaltoResource {
	
	@Autowired
	private PlanaltoRepository repository;

	@RequestMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, path="/", method = RequestMethod.POST)
	@Transactional
	public PlanaltoDTO salvar(@Valid @NotNull @RequestBody PlanaltoDTO planaltoDTO) {
		
		Planalto planalto = repository.save(new Planalto(planaltoDTO.getLimiteMaximoX(), planaltoDTO.getLimiteMaximoY(), planaltoDTO.getNome()));
		planaltoDTO.setId(planalto.getId());
		
		return planaltoDTO;
	}
	
	@RequestMapping(path="/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<PlanaltoDTO> pesquisarPorId(@PathVariable("id") Integer id) {
		PlanaltoDTO planaltoDTO = null;
		
		Planalto planalto = repository.findOne(id);
		
		if(planalto!=null){
			planaltoDTO = new PlanaltoDTO(planalto);
			return ResponseEntity.ok(planaltoDTO);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(planaltoDTO);
	}
	
	@RequestMapping(path="/todos{ordenacao}", method = RequestMethod.GET)
	public ResponseEntity<List<PlanaltoDTO>> listarTodosComPaginacaoEOrdenacao(@RequestParam(defaultValue="0") int pagina, @RequestParam(defaultValue="20") int tamanho,
			@MatrixVariable(defaultValue="nome", pathVar="ordenacao") List<String> camposAOrdenar, @MatrixVariable(defaultValue="ASC", pathVar="ordenacao") String tipoOrdenacao) {
		
		Page<Planalto> paginaComPlanaltos = repository.findAll(new PageRequest(pagina, tamanho, new Sort(Direction.fromString(tipoOrdenacao), camposAOrdenar)));
		
		List<PlanaltoDTO> planaltosDTO = paginaComPlanaltos.getContent()
			.stream()
			.map(p->new PlanaltoDTO(p))
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(planaltosDTO);
	}
}
