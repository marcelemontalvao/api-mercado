package br.com.compass.avaliacao.repository;

import br.com.compass.avaliacao.entities.OfertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository extends JpaRepository<OfertaEntity, Long> {

}
