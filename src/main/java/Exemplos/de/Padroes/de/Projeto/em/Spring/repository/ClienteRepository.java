package Exemplos.de.Padroes.de.Projeto.em.Spring.repository;

import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
