package br.com.ismyburguer.cliente.adapters.repository;

import br.com.ismyburguer.cliente.adapters.converter.ClienteToClienteModelConverter;
import br.com.ismyburguer.cliente.adapters.model.ClienteModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.gateway.out.CadastrarClienteRepository;
import br.com.ismyburguer.core.adapter.out.PersistenceAdapter;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@PersistenceAdapter
public class CadastrarClienteRepositoryImpl implements CadastrarClienteRepository {
    private final ClienteRepository clienteRepository;
    private final ClienteToClienteModelConverter converter;

    public CadastrarClienteRepositoryImpl(ClienteRepository clienteRepository,
                                          ClienteToClienteModelConverter converter) {
        this.clienteRepository = clienteRepository;
        this.converter = converter;
    }

    @Override
    public UUID salvarCliente(Cliente cliente) {
        ClienteModel clienteModel = converter.convert(cliente);
        return clienteRepository.save(clienteModel).getClienteId();
    }
}
