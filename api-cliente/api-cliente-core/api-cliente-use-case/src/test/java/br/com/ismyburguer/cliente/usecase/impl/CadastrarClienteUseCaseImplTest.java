package br.com.ismyburguer.cliente.usecase.impl;

import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.gateway.out.CadastrarClienteRepository;
import br.com.ismyburguer.cliente.gateway.out.ConsultarClienteRepository;
import br.com.ismyburguer.core.exception.BusinessException;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CadastrarClienteUseCaseImplTest {

    @Mock
    private CadastrarClienteRepository cadastrarClienteRepository;

    @Mock
    private ConsultarClienteRepository consultarClienteRepository;

    @InjectMocks
    private CadastrarClienteUseCaseImpl cadastrarClienteUseCase;

    @Test
    void cadastrarCliente_Sucesso() {
        Cliente cliente = new Cliente(new Cliente.Nome("nome"), new Cliente.Email("email@example.com"), new Cliente.CPF("12345678909"));

        when(consultarClienteRepository.obterPeloEmail(cliente.getEmail().getEndereco())).thenReturn(Optional.empty());
        when(cadastrarClienteRepository.salvarCliente(cliente)).thenReturn(UUID.randomUUID());

        UUID clienteId = cadastrarClienteUseCase.cadastrar(cliente);

        assertNotNull(clienteId);
        verify(consultarClienteRepository, times(1)).obterPeloEmail(cliente.getEmail().getEndereco());
        verify(cadastrarClienteRepository, times(1)).salvarCliente(cliente);
    }

    @Test
    void cadastrarCliente_EmailJaExiste() {
        Cliente cliente = new Cliente(new Cliente.Nome("nome"), new Cliente.Email("email@example.com"), new Cliente.CPF("12345678909"));

        when(consultarClienteRepository.obterPeloEmail(cliente.getEmail().getEndereco()))
                .thenReturn(Optional.of(EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(Cliente.class)));

        assertThrows(BusinessException.class, () -> cadastrarClienteUseCase.cadastrar(cliente));

        verify(consultarClienteRepository, times(1)).obterPeloEmail(cliente.getEmail().getEndereco());
        verify(cadastrarClienteRepository, never()).salvarCliente(cliente);
    }

    @Test
    void cadastrarCliente_UsernameJaExiste() {
        Cliente cliente = new Cliente(new Cliente.Nome("nome"), new Cliente.Email("email@example.com"), new Cliente.CPF("12345678909"));
        cliente.setUsername(new Cliente.Username("username"));

        when(consultarClienteRepository.existsByUsername(cliente.getUsername().get().getUsername())).thenReturn(true);

        assertThrows(BusinessException.class, () -> cadastrarClienteUseCase.cadastrar(cliente));

        verify(consultarClienteRepository, times(1)).existsByUsername(cliente.getUsername().get().getUsername());
        verify(cadastrarClienteRepository, never()).salvarCliente(cliente);
    }

    @Test
    void cadastrarCliente_CpfJaExiste() {
        Cliente cliente = new Cliente(new Cliente.Nome("nome"), new Cliente.Email("email@example.com"), new Cliente.CPF("12345678909"));

        when(consultarClienteRepository.obterPeloCpf(cliente.getCpf().get().getNumero()))
                .thenReturn(Optional.of(EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(Cliente.class)));

        assertThrows(BusinessException.class, () -> cadastrarClienteUseCase.cadastrar(cliente));

        verify(consultarClienteRepository, times(1)).obterPeloCpf(cliente.getCpf().get().getNumero());
        verify(cadastrarClienteRepository, never()).salvarCliente(cliente);
    }
}
