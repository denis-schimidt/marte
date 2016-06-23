package com.elo7.marte.domain.model.sonda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SondaRepository extends JpaRepository<Sonda, Long>{
}
