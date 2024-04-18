package br.com.ismyburguer.cliente.web.api.converter;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.web.api.request.AlterarClienteRequest;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.in.WebConverter;
import jakarta.validation.ConstraintViolationException;

@WebConverter
public class AlterarClienteRequestConverter implements Converter<AlterarClienteRequest, Cliente> {

    public AlterarClienteRequestConverter() {
    }

    public Cliente convert(AlterarClienteRequest source) {
        if (source == null) {
            throw new ConstraintViolationException("Não foi informado o corpo da requisição", null);
        }

        if (source != null) {
            source.validate();
        }

        return new Cliente(
                new Cliente.Nome(source.getNome(), source.getSobrenome())
        );
    }
}
