package Exemplos.de.Padroes.de.Projeto.em.Spring.service.proxy;

import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Endereco;
import Exemplos.de.Padroes.de.Projeto.em.Spring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CepProxyService {

    private final Map<String, Endereco> cache = new HashMap<>();

    @Autowired
    private ViaCepService viaCepService;

    public Endereco consultar(String cep) {

        if (cache.containsKey(cep)) {
            return cache.get(cep);
        }

        Endereco endereco = viaCepService.consultarCep(cep);

        cache.put(cep, endereco);

        return endereco;
    }
}
