package Exemplos.de.Padroes.de.Projeto.em.Spring.service.frete;

import java.math.BigDecimal;

public interface FreteStrategy {

    BigDecimal calcular(BigDecimal valorProduto);
}
