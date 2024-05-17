package br.com.ismyburguer.auth.core.web.api;

import br.com.ismyburguer.auth.core.web.api.request.ConfirmarCadastroRequest;
import br.com.ismyburguer.core.auth.adapter.interfaces.in.ConfirmSignUpUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConfirmSignUpAPITest {

    @Mock
    private ConfirmSignUpUseCase confirmSignUpUseCase;

    @InjectMocks
    private ConfirmSignUpAPI confirmSignUpAPI;

    private ConfirmarCadastroRequest request;

    @BeforeEach
    public void setUp() {
        request = new ConfirmarCadastroRequest("12345678909", "password123", "verificationCode");
    }

    @Test
    public void testarConfirmarCadastroComSucesso() {
        // Act
        confirmSignUpAPI.login(request);

        // Assert
        verify(confirmSignUpUseCase, times(1)).confirmarNovoUsuario(
                request.getCpf(),
                request.getPassword(),
                request.getCode()
        );
    }

    @Test
    public void testarConfirmarCadastroComErro() {
        // Arrange
        doThrow(new RuntimeException("Erro ao confirmar cadastro")).when(confirmSignUpUseCase).confirmarNovoUsuario(
                request.getCpf(),
                request.getPassword(),
                request.getCode()
        );

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            confirmSignUpAPI.login(request);
        });

        assertEquals("Erro ao confirmar cadastro", exception.getMessage());
        verify(confirmSignUpUseCase, times(1)).confirmarNovoUsuario(
                request.getCpf(),
                request.getPassword(),
                request.getCode()
        );
    }
}
