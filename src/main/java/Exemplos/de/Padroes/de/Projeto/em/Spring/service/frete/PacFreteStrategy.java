package Exemplos.de.Padroes.de.Projeto.em.Spring.service.frete;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PacFreteStrategy implements FreteStrategy {

    @Override
    public BigDecimal calcular(BigDecimal valorProduto) {

        return valorProduto.multiply(
                new BigDecimal("0.05"));
    }
}
