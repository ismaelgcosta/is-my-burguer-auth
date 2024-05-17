package br.com.ismyburguer.core.auth.usecase.impl;

import br.com.ismyburguer.cliente.adapter.interfaces.in.CadastrarClienteUseCase;
import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.core.auth.adapter.interfaces.in.OAuthSignUpUseCase;
import br.com.ismyburguer.core.auth.entity.SignUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserSignUpUseCaseImplTest {

    @InjectMocks
    private UserSignUpUseCaseImpl userSignUpUseCase;

    @Mock
    private CadastrarClienteUseCase cadastrarClienteUseCase;

    @Mock
    private ConsultarClienteUseCase consultarClienteUseCase;

    @Mock
    private OAuthSignUpUseCase oAuthSignUpUseCase;

    @BeforeEach
    public void setUp() {
        userSignUpUseCase = new UserSignUpUseCaseImpl(cadastrarClienteUseCase, consultarClienteUseCase, oAuthSignUpUseCase);
    }

    @Test
    public void testCadastrarNovoUsuario() {
        // Arrange
        Cliente cliente = new Cliente(new Cliente.Nome("John Doe"), new Cliente.Email("john.doe@example.com"), new Cliente.CPF("123.456.789-00"));
        when(consultarClienteUseCase.existsByUsername(anyString())).thenReturn(false);
        when(cadastrarClienteUseCase.cadastrar(cliente)).thenReturn(UUID.randomUUID());
        when(consultarClienteUseCase.buscarPorId(any())).thenReturn(cliente);

        // Act
        Cliente novoCliente = userSignUpUseCase.cadastrarNovoUsuario(cliente, "password");

        // Assert
        assertNotNull(novoCliente);
        assertNotNull(novoCliente.getUsername());
        verify(consultarClienteUseCase).existsByUsername(anyString());
        verify(cadastrarClienteUseCase).cadastrar(cliente);
        verify(oAuthSignUpUseCase).signUp(any(SignUp.class));
    }

    @Test
    public void testCadastrarNovoUsuario_UsernameExistente() {
        // Arrange
        Cliente cliente = new Cliente(new Cliente.Nome("John Doe"), new Cliente.Email("john.doe@example.com"), new Cliente.CPF("123.456.789-00"));
        cliente.setUsername(new Cliente.Username("username"));
        when(consultarClienteUseCase.existsByUsername(any())).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userSignUpUseCase.cadastrarNovoUsuario(cliente, "password"));

        verify(consultarClienteUseCase, times(5)).existsByUsername(anyString());
        verify(cadastrarClienteUseCase).cadastrar(cliente);
    }
}
