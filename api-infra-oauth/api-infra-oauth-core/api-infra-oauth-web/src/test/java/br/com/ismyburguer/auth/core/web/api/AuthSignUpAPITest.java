package br.com.ismyburguer.auth.core.web.api;
import br.com.ismyburguer.auth.core.web.api.converter.BuscarClienteAuthConverter;
import br.com.ismyburguer.auth.core.web.api.converter.CadastrarUsuarioRequestConverter;
import br.com.ismyburguer.auth.core.web.api.request.UserSignUpRequest;
import br.com.ismyburguer.core.auth.adapter.interfaces.in.UserSignUpUseCase;
import br.com.ismyburguer.core.auth.entity.User;
import br.com.ismyburguer.cliente.entity.Cliente;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthSignUpAPITest {

    @Mock
    private CadastrarUsuarioRequestConverter cadastrarUsuarioRequestConverter;

    @Mock
    private BuscarClienteAuthConverter buscarClienteAuthConverter;

    @Mock
    private UserSignUpUseCase userSignUpUseCase;

    @InjectMocks
    private AuthSignUpAPI authSignUpAPI;

    private UserSignUpRequest userSignUpRequest;
    private Cliente cliente;
    private User user;

    @BeforeEach
    public void setUp() {

        userSignUpRequest = new UserSignUpRequest();
        userSignUpRequest.setNome("John Doe");
        userSignUpRequest.setEmail("john.doe@example.com");
        userSignUpRequest.setCpf("12345678901");
        userSignUpRequest.setPassword("password");

        cliente = new Cliente(
                new Cliente.Nome("John Doe"),
                new Cliente.Email("john.doe@example.com"),
                new Cliente.CPF("12345678901")
        );

        user = new User("1", "John", "Doe", "12345678901", "john.doe@example.com", "john_doe");
    }

    @Test
    public void testarCadastrarUsuarioComSucesso() {
        // Arrange
        when(cadastrarUsuarioRequestConverter.convert(userSignUpRequest)).thenReturn(cliente);
        when(userSignUpUseCase.cadastrarNovoUsuario(any(Cliente.class), anyString())).thenReturn(cliente);
        when(buscarClienteAuthConverter.convert(any(Cliente.class))).thenReturn(user);

        // Act
        User result = authSignUpAPI.login(userSignUpRequest);

        // Assert
        assertEquals(user, result);
        verify(cadastrarUsuarioRequestConverter, times(1)).convert(userSignUpRequest);
        verify(userSignUpUseCase, times(1)).cadastrarNovoUsuario(any(Cliente.class), anyString());
        verify(buscarClienteAuthConverter, times(1)).convert(any(Cliente.class));
    }

    @Test
    public void testarCadastrarUsuarioComErro() {
        // Arrange
        when(cadastrarUsuarioRequestConverter.convert(userSignUpRequest)).thenThrow(new ConstraintViolationException("Erro de validação", null));

        // Act & Assert
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            authSignUpAPI.login(userSignUpRequest);
        });

        assertEquals("Erro de validação", exception.getMessage());
        verify(cadastrarUsuarioRequestConverter, times(1)).convert(userSignUpRequest);
        verify(userSignUpUseCase, times(0)).cadastrarNovoUsuario(any(Cliente.class), anyString());
        verify(buscarClienteAuthConverter, times(0)).convert(any(Cliente.class));
    }
}
