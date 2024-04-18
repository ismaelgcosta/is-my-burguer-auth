package br.com.ismyburguer.cliente.adapters.converter;

import br.com.ismyburguer.cliente.adapters.model.ClienteModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.out.PersistenceConverter;

import java.util.Optional;

@PersistenceConverter
public class ClienteModelToClienteConverter implements Converter<ClienteModel, Cliente> {
    @Override
    public Cliente convert(ClienteModel source) {
        return new Cliente(
                new Cliente.ClienteId(source.getClienteId()),
                new Cliente.Nome(source.getNome(), source.getSobrenome()),
                new Cliente.Email(source.getEmail()),
                source.getCpf().map(Cliente.CPF::new).orElse(null),
                Optional.ofNullable(source.getUsername()).map(Cliente.Username::new).orElse(null)
        );
    }
}
