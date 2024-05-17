package br.com.ismyburguer.cliente.usecase.impl;

import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.gateway.out.ConsultarClienteRepository;
import br.com.ismyburguer.core.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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


}
