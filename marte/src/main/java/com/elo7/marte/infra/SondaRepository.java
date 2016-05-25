package com.elo7.marte.infra;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.elo7.marte.model.Sonda;

public interface SondaRepository extends PagingAndSortingRepository<Sonda, Long>{

}
