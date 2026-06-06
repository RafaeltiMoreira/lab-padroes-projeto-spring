package Exemplos.de.Padroes.de.Projeto.em.Spring.controller;

import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Pedido;
import Exemplos.de.Padroes.de.Projeto.em.Spring.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoRestController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public Iterable<Pedido> buscarTodos() {
        return pedidoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }

    @PostMapping
    public Pedido inserir(@RequestBody Pedido pedido) {
        return pedidoService.inserir(pedido);
    }
}
