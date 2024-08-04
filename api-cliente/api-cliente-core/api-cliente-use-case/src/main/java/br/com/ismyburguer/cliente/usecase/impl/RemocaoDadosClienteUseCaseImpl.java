package br.com.ismyburguer.cliente.usecase.impl;

import br.com.ismyburguer.cliente.adapter.interfaces.in.AlterarClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.RemocaoDadosClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.auth.adapter.interfaces.in.OAuthDeleteUseCase;
import br.com.ismyburguer.core.auth.entity.Delete;
import br.com.ismyburguer.core.usecase.UseCase;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@AllArgsConstructor
public class RemocaoDadosClienteUseCaseImpl implements RemocaoDadosClienteUseCase {

    private final OAuthDeleteUseCase oAuthDeleteUseCase;
    private final ConsultarClienteUseCase consultarClienteUseCase;
    private final AlterarClienteUseCase alterarClienteUseCase;

    @Override
    @Transactional
    public void remover(ConsultarClienteUseCase.ConsultaClientePorCpf query) {
        Cliente cliente = consultarClienteUseCase.buscarPorCpf(query);
        cliente.setCpf(new Cliente.CPF(StringUtils.substring(UUID.randomUUID().toString(), 0, 11)));
        cliente.setEmail(new Cliente.Email(StringUtils.substring(UUID.randomUUID().toString() + "@ismyburguer.com", 0, 11)));
        cliente.setNome(new Cliente.Nome(new Faker().artist().name()));

        cliente.getUsername()
                .map(Cliente.Username::getUsername)
                .ifPresent(username -> {
                    oAuthDeleteUseCase.delete(new Delete(username));
                    cliente.setUsername(new Cliente.Username("removed"));
                });

        alterarClienteUseCase.alterar(cliente.getClienteId().getClienteId().toString(), cliente);
    }
}
