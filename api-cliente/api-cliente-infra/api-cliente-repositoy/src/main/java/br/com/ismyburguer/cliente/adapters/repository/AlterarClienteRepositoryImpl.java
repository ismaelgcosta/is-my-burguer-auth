package br.com.ismyburguer.cliente.adapters.repository;

import br.com.ismyburguer.cliente.adapters.model.ClienteModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.gateway.out.AlterarClienteRepository;
import br.com.ismyburguer.core.adapter.out.PersistenceAdapter;
import br.com.ismyburguer.core.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@PersistenceAdapter
public class AlterarClienteRepositoryImpl implements AlterarClienteRepository {
    private final ClienteRepository clienteRepository;

    public AlterarClienteRepositoryImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void alterarCliente(String clienteId, @Valid Cliente cliente) {
        UUID uuid = UUID.fromString(clienteId);
        if(!clienteRepository.existsById(uuid)) {
            throw new EntityNotFoundException("Cliente não foi encontrado");
        }

        ClienteModel clienteModel = clienteRepository.findById(uuid).get();
        clienteModel.setNome(cliente.getNome().getNome());
        clienteModel.setSobrenome(cliente.getNome().getSobrenome());
        clienteModel.setClienteId(uuid);
        clienteRepository.save(clienteModel);
    }
}
