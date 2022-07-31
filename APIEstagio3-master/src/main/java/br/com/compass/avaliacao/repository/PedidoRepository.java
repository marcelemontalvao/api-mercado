package br.com.compass.avaliacao.repository;

import br.com.compass.avaliacao.entities.PedidoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    @Query("SELECT pedido FROM PedidoEntity pedido WHERE (:cpf IS NULL OR :cpf = pedido.cpf)")
    List<PedidoEntity> findAllPedidos(@Param("cpf") String cpf, Sort sort);

}
