package br.com.ismyburguer.cliente.adapters.converter;

import br.com.ismyburguer.cliente.adapters.model.ClienteModel;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.out.PersistenceConverter;

@PersistenceConverter
public class ClienteToClienteModelConverter implements Converter<Cliente, ClienteModel> {
    @Override
    public ClienteModel convert(Cliente source) {
        return new ClienteModel(
                source.getNome().getNome(),
                source.getNome().getSobrenome(),
                source.getEmail().getEndereco(),
                source.getCpf().map(Cliente.CPF::getNumero).orElse(null),
                source.getUsername().map(Cliente.Username::getUsername).orElse(null)
        );
    }
}
