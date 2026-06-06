package Exemplos.de.Padroes.de.Projeto.em.Spring.service.validation;

import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoValidationChain {

    @Autowired
    private ClienteValidator clienteValidator;

    @Autowired
    private CepValidator cepValidator;

    @Autowired
    private ValorValidator valorValidator;

    public void validar(Pedido pedido) {

        clienteValidator.validar(pedido);

        cepValidator.validar(pedido);

        valorValidator.validar(pedido);
    }
}
