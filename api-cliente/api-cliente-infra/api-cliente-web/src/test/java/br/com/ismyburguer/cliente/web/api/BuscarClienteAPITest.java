package br.com.ismyburguer.cliente.web.api;

import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.web.api.converter.BuscarClienteConverter;
import br.com.ismyburguer.cliente.web.api.response.BuscarClienteResponse;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarClienteAPITest {

    @Test
    void obterCliente_PorEmail_Sucesso() {
        // Arrange
        ConsultarClienteUseCase useCase = mock(ConsultarClienteUseCase.class);
        BuscarClienteConverter converter = mock(BuscarClienteConverter.class);
        BuscarClienteAPI api = new BuscarClienteAPI(useCase, converter);
        String email = "test@example.com";
        ConsultarClienteUseCase.ConsultaCliente consulta = new ConsultarClienteUseCase.ConsultaCliente(email);
        BuscarClienteResponse response = new BuscarClienteResponse();

        when(useCase.buscar(consulta)).thenReturn(EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(Cliente.class));
        when(converter.convert(any())).thenReturn(response);

        // Act
        BuscarClienteResponse result = api.obterCliente(email);

        // Assert
        assertNotNull(result);
        verify(useCase, times(1)).buscar(consulta);
        verify(converter, times(1)).convert(any());
    }

    @Test
    void obterCliente_PorCpf_Sucesso() {
        // Arrange
        ConsultarClienteUseCase useCase = mock(ConsultarClienteUseCase.class);
        BuscarClienteConverter converter = mock(BuscarClienteConverter.class);
        BuscarClienteAPI api = new BuscarClienteAPI(useCase, converter);
        String cpf = "12345678901";
        ConsultarClienteUseCase.ConsultaClientePorCpf consulta = new ConsultarClienteUseCase.ConsultaClientePorCpf(cpf);
        BuscarClienteResponse response = new BuscarClienteResponse();

        when(useCase.buscarPorCpf(consulta)).thenReturn(EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(Cliente.class));
        when(converter.convert(any())).thenReturn(response);

        // Act
        BuscarClienteResponse result = api.obterClientePorCpf(cpf);

        // Assert
        assertNotNull(result);
        verify(useCase, times(1)).buscarPorCpf(consulta);
        verify(converter, times(1)).convert(any());
    }

    @Test
    void obterCliente_PorUsername_Sucesso() {
        // Arrange
        ConsultarClienteUseCase useCase = mock(ConsultarClienteUseCase.class);
        BuscarClienteConverter converter = mock(BuscarClienteConverter.class);
        BuscarClienteAPI api = new BuscarClienteAPI(useCase, converter);
        String username = "john_doe";
        ConsultarClienteUseCase.ConsultaClientePorUsername consulta = new ConsultarClienteUseCase.ConsultaClientePorUsername(username);
        BuscarClienteResponse response = new BuscarClienteResponse();

        when(useCase.buscarPorUsername(consulta)).thenReturn(EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(Cliente.class));
        when(converter.convert(any())).thenReturn(response);

        // Act
        BuscarClienteResponse result = api.obterClientePorUsername(username);

        // Assert
        assertNotNull(result);
        verify(useCase, times(1)).buscarPorUsername(consulta);
        verify(converter, times(1)).convert(any());
    }

    @Test
    void obterCliente_PorId_Sucesso() {
        // Arrange
        ConsultarClienteUseCase useCase = mock(ConsultarClienteUseCase.class);
        BuscarClienteConverter converter = mock(BuscarClienteConverter.class);
        BuscarClienteAPI api = new BuscarClienteAPI(useCase, converter);
        String clienteId = "123";
        ConsultarClienteUseCase.ConsultaClientePorId consulta = new ConsultarClienteUseCase.ConsultaClientePorId(clienteId);
        BuscarClienteResponse response = new BuscarClienteResponse();

        when(useCase.buscarPorId(consulta)).thenReturn(EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(Cliente.class));
        when(converter.convert(any())).thenReturn(response);

        // Act
        BuscarClienteResponse result = api.obterClientePorId(clienteId);

        // Assert
        assertNotNull(result);
        verify(useCase, times(1)).buscarPorId(consulta);
        verify(converter, times(1)).convert(any());
    }
}
