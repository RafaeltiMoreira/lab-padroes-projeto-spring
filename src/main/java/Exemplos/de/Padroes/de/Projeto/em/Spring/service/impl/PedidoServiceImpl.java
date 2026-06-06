package Exemplos.de.Padroes.de.Projeto.em.Spring.service.impl;

import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Cliente;
import Exemplos.de.Padroes.de.Projeto.em.Spring.repository.ClienteRepository;
import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Pedido;
import Exemplos.de.Padroes.de.Projeto.em.Spring.repository.PedidoRepository;
import Exemplos.de.Padroes.de.Projeto.em.Spring.service.PedidoService;
import Exemplos.de.Padroes.de.Projeto.em.Spring.service.frete.FreteStrategy;
import Exemplos.de.Padroes.de.Projeto.em.Spring.service.frete.FreteStrategyFactory;
import Exemplos.de.Padroes.de.Projeto.em.Spring.service.proxy.CepProxyService;
import Exemplos.de.Padroes.de.Projeto.em.Spring.service.validation.PedidoValidationChain;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository repository;
    private final FreteStrategyFactory strategyFactory;
    private final PedidoValidationChain validationChain;
    private final CepProxyService cepProxyService;

    public PedidoServiceImpl(
            ClienteRepository clienteRepository,
            PedidoRepository repository,
            FreteStrategyFactory strategyFactory,
            PedidoValidationChain validationChain,
            CepProxyService cepProxyService) {

        this.clienteRepository = clienteRepository;
        this.repository = repository;
        this.strategyFactory = strategyFactory;
        this.validationChain = validationChain;
        this.cepProxyService = cepProxyService;
    }

    @Override public Iterable<Pedido> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Pedido inserir(Pedido pedido) {
        validationChain.validar(pedido);
        Cliente cliente =
                clienteRepository.findById(
                        pedido.getCliente().getId())
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        String cep =
                cliente.getEndereco()
                        .getCep();

        cepProxyService.consultar(cep);

        FreteStrategy strategy = strategyFactory.obter(
                pedido.getModalidadeEntrega());

        BigDecimal frete = strategy.calcular(
                pedido.getValorProduto());

        pedido.setCliente(cliente);

        pedido.setValorFrete(frete);

        pedido.setValorTotal(
                pedido.getValorProduto()
                        .add(frete));

        return repository.save(pedido);
    }
}
