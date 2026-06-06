package Exemplos.de.Padroes.de.Projeto.em.Spring.service.validation;

import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class ClienteValidator implements PedidoValidator {

    @Override
    public void validar(Pedido pedido) {

        if (pedido.getCliente() == null) {

            throw new RuntimeException(
                    "Cliente obrigatório");
        }
    }
}
