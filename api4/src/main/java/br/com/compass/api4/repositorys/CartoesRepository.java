package br.com.compass.api4.repositorys;

import br.com.compass.api4.entities.CartoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartoesRepository extends JpaRepository<CartoesEntity, Long> {
}
