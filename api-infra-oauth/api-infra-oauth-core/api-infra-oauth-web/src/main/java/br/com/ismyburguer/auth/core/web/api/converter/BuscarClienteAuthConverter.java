package br.com.ismyburguer.auth.core.web.api.converter;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.in.WebConverter;
import br.com.ismyburguer.core.auth.entity.User;
import jakarta.validation.ConstraintViolationException;

@WebConverter
public class BuscarClienteAuthConverter implements Converter<Cliente, User> {
    @Override
    public User convert(Cliente source) {
        if(source == null) {
            throw new ConstraintViolationException("Não foi informado o corpo da requisição", null);
        }

        if(source != null) {
            source.validate();
        }

        return new User(
                source.getClienteId().getClienteId().toString(),
                source.getNome().getNome(),
                source.getNome().getSobrenome(),
                source.getCpf().map(Cliente.CPF::getNumero).orElse(null),
                source.getEmail().getEndereco(),
                source.getUsername().map(Cliente.Username::getUsername).orElse(null)
        );
    }
}
