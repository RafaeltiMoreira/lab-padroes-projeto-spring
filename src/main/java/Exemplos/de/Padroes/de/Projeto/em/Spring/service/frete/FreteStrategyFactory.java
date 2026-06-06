package Exemplos.de.Padroes.de.Projeto.em.Spring.service.frete;

import Exemplos.de.Padroes.de.Projeto.em.Spring.model.ModalidadeEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FreteStrategyFactory {

    @Autowired
    private PacFreteStrategy pac;

    @Autowired
    private SedexFreteStrategy sedex;

    @Autowired
    private ExpressoFreteStrategy expresso;

    public FreteStrategy obter(ModalidadeEntrega modalidade) {

        return switch (modalidade) {

            case PAC -> pac;
            case SEDEX -> sedex;
            case EXPRESSO -> expresso;
        };
    }
}
