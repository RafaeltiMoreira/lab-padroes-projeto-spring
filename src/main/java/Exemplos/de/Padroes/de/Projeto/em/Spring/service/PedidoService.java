package Exemplos.de.Padroes.de.Projeto.em.Spring.service;

import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Pedido;

public interface PedidoService {

    Iterable<Pedido> buscarTodos();

    Pedido buscarPorId(Long id);

    Pedido inserir(Pedido pedido);
}
