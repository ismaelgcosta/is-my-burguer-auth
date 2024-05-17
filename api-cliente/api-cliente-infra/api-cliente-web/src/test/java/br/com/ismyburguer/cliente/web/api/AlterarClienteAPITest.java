package br.com.ismyburguer.cliente.web.api;

import br.com.ismyburguer.cliente.adapter.interfaces.in.AlterarClienteUseCase;
import br.com.ismyburguer.cliente.web.api.converter.AlterarClienteRequestConverter;
import br.com.ismyburguer.cliente.web.api.request.AlterarClienteRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlterarClienteAPITest {

    @Test
    void alterarCliente_SolicitacaoValida_ChamaCasoDeUso() {
        // Arrange
        AlterarClienteUseCase useCase = mock(AlterarClienteUseCase.class);
        AlterarClienteRequestConverter converter = mock(AlterarClienteRequestConverter.class);
        AlterarClienteAPI api = new AlterarClienteAPI(useCase, converter);
        String clienteId = "123";
        AlterarClienteRequest request = new AlterarClienteRequest();
        request.setNome("John");
        request.setSobrenome("Doe");

        // Act
        api.alterarCliente(clienteId, request);

        // Assert
        verify(useCase, times(1)).alterar(clienteId, converter.convert(request));
    }

}
