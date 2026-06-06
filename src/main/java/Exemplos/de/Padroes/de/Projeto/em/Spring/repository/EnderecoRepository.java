package Exemplos.de.Padroes.de.Projeto.em.Spring.repository;

import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
}
