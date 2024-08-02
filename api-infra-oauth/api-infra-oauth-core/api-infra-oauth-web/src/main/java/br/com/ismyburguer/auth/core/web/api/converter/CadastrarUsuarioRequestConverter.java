package br.com.ismyburguer.auth.core.web.api.converter;

import br.com.ismyburguer.auth.core.web.api.request.UserSignUpRequest;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.adapter.Converter;
import br.com.ismyburguer.core.adapter.in.WebConverter;
import jakarta.validation.ConstraintViolationException;

import java.util.Optional;

@WebConverter
public class CadastrarUsuarioRequestConverter implements Converter<UserSignUpRequest, Cliente> {

    public Cliente convert(UserSignUpRequest source) {
        if(source == null) {
            throw new ConstraintViolationException("Não foi informado o corpo da requisição", null);
        }

        if(source != null) {
            source.validate();
        }

        return new Cliente(
                new Cliente.Nome(source.getNome()),
                new Cliente.Email(source.getEmail()),
                Optional.of(source.getCpf()).map(Cliente.CPF::new).orElse(null)
        );
    }
}
