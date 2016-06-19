package com.elo7.marte.domain.sonda;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SondaRepository extends PagingAndSortingRepository<Sonda, Long>{}
