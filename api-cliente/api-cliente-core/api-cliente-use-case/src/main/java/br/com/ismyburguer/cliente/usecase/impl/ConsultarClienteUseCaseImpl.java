package br.com.ismyburguer.cliente.usecase.impl;

import br.com.ismyburguer.cliente.adapter.interfaces.in.ConsultarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.gateway.out.ConsultarClienteRepository;
import br.com.ismyburguer.core.exception.EntityNotFoundException;
import br.com.ismyburguer.core.usecase.UseCase;

import java.util.UUID;

@UseCase
public class ConsultarClienteUseCaseImpl implements ConsultarClienteUseCase {

    private final ConsultarClienteRepository consultarClienteRepository;

    public ConsultarClienteUseCaseImpl(ConsultarClienteRepository consultarClienteRepository) {
        this.consultarClienteRepository = consultarClienteRepository;
    }

    @Override
    public Cliente buscar(ConsultaCliente query) {
        return consultarClienteRepository.obterPeloEmail(query.email())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não foi encontrado"));
    }

    @Override
    public Cliente buscarPorCpf(ConsultaClientePorCpf query) {
        return consultarClienteRepository.obterPeloCpf(query.cpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não foi encontrado"));
    }
    @Override
    public Cliente buscarPorId(ConsultaClientePorId query) {
        return consultarClienteRepository.obterPeloClienteId(UUID.fromString(query.clienteId()))
                .orElseThrow(() -> new EntityNotFoundException("Cliente não foi encontrado"));
    }

    @Override
    public Cliente buscarPorUsername(ConsultaClientePorUsername query) {
        return consultarClienteRepository.obterPeloClienteUsername(query.username())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não foi encontrado"));
    }

    @Override
    public boolean existsById(UUID id) {
        return consultarClienteRepository.existsById(id);
    }
    @Override
    public boolean existsByUsername(String username) {
        return consultarClienteRepository.existsByUsername(username);
    }
    @Override
    public boolean existsByCpf(String cpf) {
        return consultarClienteRepository.existsByCpf(cpf);
    }
}
