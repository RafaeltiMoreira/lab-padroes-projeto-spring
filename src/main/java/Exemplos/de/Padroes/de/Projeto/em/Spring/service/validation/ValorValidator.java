package Exemplos.de.Padroes.de.Projeto.em.Spring.service.validation;

import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class ValorValidator implements PedidoValidator {

    @Override
    public void validar(Pedido pedido) {

        if (pedido.getValorProduto() == null
                || pedido.getValorProduto().doubleValue() <= 0) {

            throw new RuntimeException(
                    "Valor inválido");
        }
    }
}
