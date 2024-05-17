package br.com.ismyburguer.cliente.web.api;

import br.com.ismyburguer.cliente.adapter.interfaces.in.CadastrarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.web.api.converter.CadastrarClienteRequestConverter;
import br.com.ismyburguer.cliente.web.api.request.CriarClienteRequest;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class CadastrarClienteAPITest {


    @Test
    void cadastrarCliente_ValidRequest_ReturnsUUID() {
        // Arrange
        CadastrarClienteUseCase useCase = Mockito.mock(CadastrarClienteUseCase.class);
        CadastrarClienteRequestConverter converter = Mockito.mock(CadastrarClienteRequestConverter.class);
        CadastrarClienteAPI api = new CadastrarClienteAPI(useCase, converter);
        CriarClienteRequest request = new CriarClienteRequest();
        UUID expectedUUID = UUID.randomUUID();

        when(converter.convert(request)).thenReturn(EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(Cliente.class));
        when(useCase.cadastrar(any())).thenReturn(expectedUUID);

        // Act
        UUID result = api.cadastrarCliente(request);

        // Assert
        assertEquals(expectedUUID, result);
        verify(converter, times(1)).convert(request);
        verify(useCase, times(1)).cadastrar(any());
    }
}