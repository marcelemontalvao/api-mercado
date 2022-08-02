package br.com.compass.pagamentosapi.repository;

import br.com.compass.pagamentosapi.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity,Long> {
}
