package br.com.ismyburguer.cliente.usecase.impl;

import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.gateway.out.ConsultarClienteRepository;
import br.com.ismyburguer.core.exception.EntityNotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarClienteUseCaseImplTest {

    @Mock
    private ConsultarClienteRepository consultarClienteRepository;

    @InjectMocks
    private ConsultarClienteUseCaseImpl consultarClienteUseCase;

    @Test
    void buscarPorEmail_ClienteEncontrado() {
        ConsultarClienteUseCase.ConsultaCliente query = new ConsultarClienteUseCase.ConsultaCliente("email@example.com");
        Cliente cliente = new Cliente(new Cliente.Nome("nome"), new Cliente.Email("email@example.com"), new Cliente.CPF("12345678909"));
        when(consultarClienteRepository.obterPeloEmail(query.email())).thenReturn(Optional.of(cliente));

        Cliente result = consultarClienteUseCase.buscar(query);

        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    void buscarPorEmail_ClienteNaoEncontrado() {
        ConsultarClienteUseCase.ConsultaCliente query = new ConsultarClienteUseCase.ConsultaCliente("email@example.com");
        when(consultarClienteRepository.obterPeloEmail(query.email())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> consultarClienteUseCase.buscar(query));
    }

    @Test
    void buscarPorCpf_ClienteEncontrado() {
        ConsultarClienteUseCase.ConsultaClientePorCpf query = new ConsultarClienteUseCase.ConsultaClientePorCpf("12345678909");
        Cliente cliente = new Cliente(new Cliente.Nome("nome"), new Cliente.Email("email@example.com"), new Cliente.CPF("12345678909"));
        when(consultarClienteRepository.obterPeloCpf(query.cpf())).thenReturn(Optional.of(cliente));

        Cliente result = consultarClienteUseCase.buscarPorCpf(query);

        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    void buscarPorCpf_ClienteNaoEncontrado() {
        ConsultarClienteUseCase.ConsultaClientePorCpf query = new ConsultarClienteUseCase.ConsultaClientePorCpf("12345678900");
        when(consultarClienteRepository.obterPeloCpf(query.cpf())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> consultarClienteUseCase.buscarPorCpf(query));
    }

    @Test
    void buscar_DeveRetornarCliente_QuandoEmailExiste() {
        // Arrange
        Cliente cliente = new Cliente(new Cliente.Nome("nome")); // Assumindo que Cliente possui um construtor padrão
        when(consultarClienteRepository.obterPeloEmail(anyString())).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = consultarClienteUseCase.buscar(new ConsultarClienteUseCase.ConsultaCliente("test@example.com"));

        // Assert
        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    void buscar_DeveLancarExcecao_QuandoEmailNaoExiste() {
        // Arrange
        when(consultarClienteRepository.obterPeloEmail(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () ->
                consultarClienteUseCase.buscar(new ConsultarClienteUseCase.ConsultaCliente("test@example.com")));
    }

    @Test
    void buscarPorCpf_DeveRetornarCliente_QuandoCpfExiste() {
        // Arrange
        Cliente cliente = new Cliente(new Cliente.Nome("nome"));
        when(consultarClienteRepository.obterPeloCpf(anyString())).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = consultarClienteUseCase.buscarPorCpf(new ConsultarClienteUseCase.ConsultaClientePorCpf("12345678901"));

        // Assert
        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    void buscarPorCpf_DeveLancarExcecao_QuandoCpfNaoExiste() {
        // Arrange
        when(consultarClienteRepository.obterPeloCpf(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () ->
                consultarClienteUseCase.buscarPorCpf(new ConsultarClienteUseCase.ConsultaClientePorCpf("12345678901")));
    }

    @Test
    void buscarPorId_DeveRetornarCliente_QuandoIdExiste() {
        // Arrange
        Cliente cliente = new Cliente(new Cliente.Nome("nome"));
        UUID clienteId = UUID.randomUUID();
        when(consultarClienteRepository.obterPeloClienteId(any())).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = consultarClienteUseCase.buscarPorId(new ConsultarClienteUseCase.ConsultaClientePorId(clienteId.toString()));

        // Assert
        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    void buscarPorId_DeveLancarExcecao_QuandoIdNaoExiste() {
        // Arrange
        UUID clienteId = UUID.randomUUID();
        when(consultarClienteRepository.obterPeloClienteId(any())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () ->
                consultarClienteUseCase.buscarPorId(new ConsultarClienteUseCase.ConsultaClientePorId(clienteId.toString())));
    }

    @Test
    void buscarPorUsername_DeveRetornarCliente_QuandoUsernameExiste() {
        // Arrange
        Cliente cliente = new Cliente(new Cliente.Nome("nome"));
        when(consultarClienteRepository.obterPeloClienteUsername(anyString())).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = consultarClienteUseCase.buscarPorUsername(new ConsultarClienteUseCase.ConsultaClientePorUsername("username"));

        // Assert
        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    void buscarPorUsername_DeveLancarExcecao_QuandoUsernameNaoExiste() {
        // Arrange
        when(consultarClienteRepository.obterPeloClienteUsername(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () ->
                consultarClienteUseCase.buscarPorUsername(new ConsultarClienteUseCase.ConsultaClientePorUsername("username")));
    }

    @Test
    void existsById_DeveRetornarVerdadeiro_QuandoIdExiste() {
        // Arrange
        UUID clienteId = UUID.randomUUID();
        when(consultarClienteRepository.existsById(any())).thenReturn(true);

        // Act
        boolean result = consultarClienteUseCase.existsById(clienteId);

        // Assert
        assertTrue(result);
    }

    @Test
    void existsById_DeveRetornarFalso_QuandoIdNaoExiste() {
        // Arrange
        UUID clienteId = UUID.randomUUID();
        when(consultarClienteRepository.existsById(any())).thenReturn(false);

        // Act
        boolean result = consultarClienteUseCase.existsById(clienteId);

        // Assert
        assertFalse(result);
    }

    @Test
    void existsByUsername_DeveRetornarVerdadeiro_QuandoUsernameExiste() {
        // Arrange
        when(consultarClienteRepository.existsByUsername(anyString())).thenReturn(true);

        // Act
        boolean result = consultarClienteUseCase.existsByUsername("username");

        // Assert
        assertTrue(result);
    }

    @Test
    void existsByUsername_DeveRetornarFalso_QuandoUsernameNaoExiste() {
        // Arrange
        when(consultarClienteRepository.existsByUsername(anyString())).thenReturn(false);

        // Act
        boolean result = consultarClienteUseCase.existsByUsername("username");

        // Assert
        assertFalse(result);
    }

    @Test
    void existsByCpf_DeveRetornarVerdadeiro_QuandoCpfExiste() {
        // Arrange
        when(consultarClienteRepository.existsByCpf(anyString())).thenReturn(true);

        // Act
        boolean result = consultarClienteUseCase.existsByCpf("12345678901");

        // Assert
        assertTrue(result);
    }

    @Test
    void existsByCpf_DeveRetornarFalso_QuandoCpfNaoExiste() {
        // Arrange
        when(consultarClienteRepository.existsByCpf(anyString())).thenReturn(false);

        // Act
        boolean result = consultarClienteUseCase.existsByCpf("12345678901");

        // Assert
        assertFalse(result);
    }
}
