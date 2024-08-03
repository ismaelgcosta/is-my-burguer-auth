package br.com.ismyburguer.cliente.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemocaoDadosClienteUseCaseImplTest {

    @Mock
    private OAuthDeleteUseCase oAuthDeleteUseCase;

    @Mock
    private ConsultarClienteUseCase consultarClienteUseCase;

    @Mock
    private AlterarClienteUseCase alterarClienteUseCase;

    @InjectMocks
    private RemocaoDadosClienteUseCaseImpl remocaoDadosClienteUseCase;

    @Test
    void remover_DeveRemoverDadosDoCliente() {
        // Arrange
        String cpf = "12345678900";
        ConsultarClienteUseCase.ConsultaClientePorCpf query = new ConsultarClienteUseCase.ConsultaClientePorCpf(cpf);
        Cliente cliente = new Cliente(new Cliente.Nome("FirstName", "LastName"));
        cliente.setClienteId(new Cliente.ClienteId(UUID.randomUUID()));
        cliente.setCpf(new Cliente.CPF(cpf));
        cliente.setEmail(new Cliente.Email("email@domain.com"));
        cliente.setUsername(new Cliente.Username("username"));

        when(consultarClienteUseCase.buscarPorCpf(query)).thenReturn(cliente);

        // Act
        remocaoDadosClienteUseCase.remover(query);

        // Assert
        verify(oAuthDeleteUseCase, times(1)).delete(any(Delete.class));
        verify(alterarClienteUseCase, times(1)).alterar(anyString(), any(Cliente.class));

        assertNotEquals("12345678900", cliente.getCpf().get().getNumero());
        assertNotEquals("email@domain.com", cliente.getEmail().getEndereco());
        assertNotEquals("FirstName", cliente.getNome().getNome());
        assertNotEquals("LastName", cliente.getNome().getSobrenome());
        assertEquals("removed", cliente.getUsername().get().getUsername());
    }
}
