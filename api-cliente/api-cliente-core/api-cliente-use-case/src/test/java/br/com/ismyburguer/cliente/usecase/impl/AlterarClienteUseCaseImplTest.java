package br.com.ismyburguer.cliente.usecase.impl;

import br.com.ismyburguer.cliente.adapter.interfaces.in.AlterarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.gateway.out.AlterarClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AlterarClienteUseCaseImplTest {

    @Mock
    AlterarClienteRepository mockRepository;

    AlterarClienteUseCase alterarClienteUseCase;

    @BeforeEach
    void setUp() {
        alterarClienteUseCase = new AlterarClienteUseCaseImpl(mockRepository);
    }

    @Test
    void alterar_DeveChamarMetodoDoRepositorioComParametrosCorretos_QuandoClienteExistente() {
        // Arrange
        String clienteId = UUID.randomUUID().toString();
        Cliente cliente = new Cliente(new Cliente.Nome("nome"));

        // Act
        alterarClienteUseCase.alterar(clienteId, cliente);

        // Assert
        verify(mockRepository, times(1)).alterarCliente(clienteId, cliente);
    }
}
