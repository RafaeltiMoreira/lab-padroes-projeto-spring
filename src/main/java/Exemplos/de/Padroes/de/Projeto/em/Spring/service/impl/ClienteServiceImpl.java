package Exemplos.de.Padroes.de.Projeto.em.Spring.service.impl;

import Exemplos.de.Padroes.de.Projeto.em.Spring.exception.ClienteNaoEncontradoException;
import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Cliente;
import Exemplos.de.Padroes.de.Projeto.em.Spring.repository.ClienteRepository;
import Exemplos.de.Padroes.de.Projeto.em.Spring.model.Endereco;
import Exemplos.de.Padroes.de.Projeto.em.Spring.repository.EnderecoRepository;
import org.springframework.stereotype.Service;
import Exemplos.de.Padroes.de.Projeto.em.Spring.service.ClienteService;
import Exemplos.de.Padroes.de.Projeto.em.Spring.service.ViaCepService;

//import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    /*
    Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;
    */
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final ViaCepService viaCepService;

    public ClienteServiceImpl(
            ClienteRepository clienteRepository,
            EnderecoRepository enderecoRepository,
            ViaCepService viaCepService) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        // Buscar Cliente por ID.
        /*
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
        */
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ClienteNaoEncontradoException(id));
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // Buscar Cliente por ID, caso exista:
        /*
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
        */
        Cliente clienteBd = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ClienteNaoEncontradoException(id));

        cliente.setId(clienteBd.getId());

        salvarClienteComCep(cliente);
    }

    @Override
    public void deletar(Long id) {
        // Deletar Cliente por ID.
        // clienteRepository.deleteById(id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ClienteNaoEncontradoException(id));

        clienteRepository.delete(cliente);
    }

    private void salvarClienteComCep(Cliente cliente) {
        // Verificar se o Endereco do Cliente já existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }
}
