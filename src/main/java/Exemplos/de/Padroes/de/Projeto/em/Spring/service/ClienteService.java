package Exemplos.de.Padroes.de.Projeto.em.Spring.service;

import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Cliente;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);
}
