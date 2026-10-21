package br.unipar.backend.minhaapi.repository;

import br.unipar.backend.minhaapi.model.Divida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DividaRepository extends JpaRepository<Divida, Integer> {

}
