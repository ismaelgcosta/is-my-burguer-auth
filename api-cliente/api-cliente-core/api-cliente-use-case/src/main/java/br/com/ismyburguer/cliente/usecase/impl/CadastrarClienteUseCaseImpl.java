package br.com.ismyburguer.cliente.usecase.impl;

import br.com.ismyburguer.cliente.adapter.interfaces.in.CadastrarClienteUseCase;
import br.com.ismyburguer.cliente.entity.Cliente;
import br.com.ismyburguer.cliente.gateway.out.CadastrarClienteRepository;
import br.com.ismyburguer.cliente.gateway.out.ConsultarClienteRepository;
import br.com.ismyburguer.core.exception.BusinessException;
import br.com.ismyburguer.core.usecase.UseCase;

import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@UseCase
public class CadastrarClienteUseCaseImpl implements CadastrarClienteUseCase {
    private final CadastrarClienteRepository repository;
    private final ConsultarClienteRepository consultarClienteRepository;

    public CadastrarClienteUseCaseImpl(CadastrarClienteRepository repository,
                                       ConsultarClienteRepository consultarClienteRepository) {
        this.repository = repository;
        this.consultarClienteRepository = consultarClienteRepository;
    }

    @Override
    public UUID cadastrar(Cliente cliente) {

        Optional<Cliente.Username> username = cliente.getUsername();
        if (username.isPresent() &&
                consultarClienteRepository.existsByUsername(username.get().getUsername())) {
            throw new BusinessException("O username informado já existe");
        }

        if (cliente.getEmail() != null &&
                isNotBlank(cliente.getEmail().getEndereco()) &&
                consultarClienteRepository.obterPeloEmail(cliente.getEmail().getEndereco())
                        .filter(cli -> !cli.getClienteId().equals(cliente.getClienteId()))
                        .isPresent()
        ) {
            throw new BusinessException("O e-mail informado já existe");
        }

        Optional<Cliente.CPF> cpf = cliente.getCpf();
        if (cpf.isPresent() &&
                isNotBlank(cpf.get().getNumero()) &&
                consultarClienteRepository.obterPeloCpf(cpf.get().getNumero())
                        .filter(cli -> !cli.getClienteId().equals(cliente.getClienteId()))
                        .isPresent()
        ) {
            throw new BusinessException("O CPF informado já existe");
        }

        return repository.salvarCliente(cliente);
    }
}
