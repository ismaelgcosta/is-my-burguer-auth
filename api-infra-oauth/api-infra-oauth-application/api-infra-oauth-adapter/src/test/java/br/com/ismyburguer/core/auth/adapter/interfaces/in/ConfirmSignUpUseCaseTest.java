package br.com.ismyburguer.core.auth.adapter.interfaces.in;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.auth.entity.ConfirmSignUp;
import br.com.ismyburguer.core.exception.BusinessException;
import br.com.ismyburguer.core.usecase.UseCase;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
public class ConfirmSignUpUseCaseTest {

    private ConfirmSignUpUseCase confirmSignUpUseCase;

    @Mock
    private ConsultarClienteUseCase consultarClienteUseCase;

    @Mock
    private OAuthConfirmSignUpUseCase oAuthConfirmSignUpUseCase;

    @BeforeEach
    public void setUp() {
        confirmSignUpUseCase = new ConfirmSignUpUseCase(consultarClienteUseCase, oAuthConfirmSignUpUseCase);
    }

    @Test
    public void deveConfirmarNovoUsuario() {
        // Arrange
        Cliente clienteMock = EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(Cliente.class);
        Cliente.CPF cpf = new Cliente.CPF("99999999999");
        clienteMock.setCpf(cpf);
        String cpfFormatado = new CPFFormatter().isFormatted(cpf.getNumero()) ? cpf.getNumero() : new CPFFormatter().format(cpf.getNumero());
        when(consultarClienteUseCase.buscarPorCpf(any())).thenReturn(clienteMock);

        // Act
        confirmSignUpUseCase.confirmarNovoUsuario(cpf.getNumero(), "senha", "codigo");

        // Assert
        verify(consultarClienteUseCase).buscarPorCpf(any());
        verify(oAuthConfirmSignUpUseCase).confirmSignUp(any());
    }

    @Test
    public void deveLancarExcecaoQuandoNaoEncontrarUsername() {
        // Arrange
        String cpf = "12345678900";
        Cliente cliente = EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(Cliente.class);
        cliente.setUsername(null);
        when(consultarClienteUseCase.buscarPorCpf(any())).thenReturn(cliente);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () ->
                confirmSignUpUseCase.confirmarNovoUsuario(cpf, "senha", "codigo"));
        assertEquals("Não foi encontrado um Username associado ao Cliente informado", exception.getMessage());

        verify(consultarClienteUseCase).buscarPorCpf(any());
        verifyNoInteractions(oAuthConfirmSignUpUseCase);
    }
}
