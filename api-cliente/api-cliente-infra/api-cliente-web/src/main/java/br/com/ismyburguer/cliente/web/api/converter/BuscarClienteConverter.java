package br.com.ismyburguer.cliente.web.api.converter;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.web.api.response.BuscarClienteResponse;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.in.WebConverter;
import jakarta.validation.ConstraintViolationException;

@WebConverter
public class BuscarClienteConverter implements Converter<Cliente, BuscarClienteResponse> {
    @Override
    public BuscarClienteResponse convert(Cliente source) {
        if(source == null) {
            throw new ConstraintViolationException("Não foi informado o corpo da requisição", null);
        }

        if(source != null) {
            source.validate();
        }

        return new BuscarClienteResponse(
                source.getClienteId().getClienteId().toString(),
                source.getNome().getNome(),
                source.getNome().getSobrenome(),
                source.getCpf().map(Cliente.CPF::getNumero).orElse(null),
                source.getEmail().getEndereco(),
                source.getUsername().map(Cliente.Username::getUsername).orElse(null)
        );
    }
}
