package br.com.ismyburguer.cliente.adapters.repository;

import br.com.ismyburguer.cliente.adapters.converter.ClienteModelToClienteConverter;
import br.com.ismyburguer.cliente.adapters.model.ClienteModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.gateway.out.ConsultarClienteRepository;
import br.com.ismyburguer.core.adapter.out.PersistenceAdapter;

import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
public class ConsultarClienteRepositoryImpl implements ConsultarClienteRepository {
    private final ClienteRepository clienteRepository;
    private final ClienteModelToClienteConverter converter;

    public ConsultarClienteRepositoryImpl(ClienteRepository clienteRepository,
                                          ClienteModelToClienteConverter converter) {
        this.clienteRepository = clienteRepository;
        this.converter = converter;
    }

    @Override
    public Optional<Cliente> obterPeloClienteId(UUID clienteId) {
        Optional<ClienteModel> clienteEntity = clienteRepository.findById(clienteId);
        return clienteEntity.map(converter::convert);
    }

    @Override
    public Optional<Cliente> obterPeloEmail(String email) {
        return clienteRepository.findByEmail(email)
                .map(converter::convert);
    }

    @Override
    public Optional<Cliente> obterPeloClienteUsername(String username) {
        return clienteRepository.findByUsername(username)
                .map(converter::convert);
    }

    @Override
    public Optional<Cliente> obterPeloCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .map(converter::convert);
    }

    @Override
    public boolean existsById(UUID clienteId) {
        return clienteRepository.existsById(clienteId);
    }

    @Override
    public boolean existsByUsername(String username) {
        return clienteRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }

}
