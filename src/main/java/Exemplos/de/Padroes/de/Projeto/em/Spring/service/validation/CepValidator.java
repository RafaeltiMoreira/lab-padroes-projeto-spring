package Exemplos.de.Padroes.de.Projeto.em.Spring.service.validation;

import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class CepValidator implements PedidoValidator {

    @Override
    public void validar(Pedido pedido) {

        if (pedido.getCliente()
                .getEndereco()
                .getCep() == null) {

            throw new RuntimeException(
                    "CEP obrigatório");
        }
    }
}
